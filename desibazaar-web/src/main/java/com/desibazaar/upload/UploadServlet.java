package com.desibazaar.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

public class UploadServlet extends HttpServlet {
	public static String UPLOAD_DIR;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			UPLOAD_DIR = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/img/";
			int resumableChunkNumber = 0;
			int resumableChunkSize = 0;
			long resumableTotalSize = 0;
			String resumableIdentifier = null;
			String resumableFilename = null;
			String resumableRelativePath = null;
			InputStream is = null;

			for (Part part : request.getParts()) {
				switch (part.getName()) {
				case "flowChunkNumber":
					resumableChunkNumber = Integer.parseInt(getValue(part));
					break;
				case "flowChunkSize":
					resumableChunkSize = Integer.parseInt(getValue(part));
					break;
				case "flowTotalSize":
					resumableTotalSize = Long.parseLong(getValue(part));
					break;
				case "flowIdentifier":
					resumableIdentifier = getValue(part);
					break;
				case "flowFilename":
					resumableFilename = getValue(part);
					break;
				case "flowRelativePath":
					resumableRelativePath = getValue(part);
					break;
				default:
					is = part.getInputStream();
				}
			}
			String resumableFilePath = new File(UPLOAD_DIR, resumableIdentifier)
					.getAbsolutePath() + ".temp";

			ResumableInfoStorage storage = ResumableInfoStorage.getInstance();

			ResumableInfo info = storage.get(resumableChunkSize,
					resumableTotalSize, resumableIdentifier, resumableFilename,
					resumableRelativePath, resumableFilePath);

			RandomAccessFile raf = new RandomAccessFile(info.resumableFilePath,
					"rw");

			// Seek to position
			raf.seek((resumableChunkNumber - 1) * info.resumableChunkSize);

			// Save to file
			long readed = 0;
			long content_length = request.getContentLength();
			byte[] bytes = new byte[1024 * 100];
			while (readed < content_length) {
				int r = is.read(bytes);
				if (r < 0) {
					break;
				}
				raf.write(bytes, 0, r);
				readed += r;
			}
			raf.close();

			// Mark as uploaded.
			info.uploadedChunks.add(new ResumableInfo.ResumableChunkNumber(
					resumableChunkNumber));
			if (info.checkIfUploadFinished()) {
				ResumableInfoStorage.getInstance().remove(info);
				response.getWriter().print("All finished.");
			} else {
				response.getWriter().print("Upload");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	private String getValue(Part part) throws IOException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(part.getInputStream(), writer);
		return writer.toString();
	}
}
