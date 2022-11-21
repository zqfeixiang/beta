package com.dong.beta.email;
import com.dong.beta.entity.Article;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**

 - @author dzq
 - @since 11/21/22 20:52
 */
public class AttachmentGenerator {

    public static byte[] getAttachmentByte(List<Article> articleList) {
        StringBuilder builder = new StringBuilder();
        for (Article article : articleList){
            builder.append("id:").append(article.getId()).append("\r\n")
                    .append("author: ").append(article.getAuthor()).append("\r\n")
                    .append("content: ").append(article.getContent()).append("\r\n");
        }
        return builder.toString().getBytes();
    }

    public static File getAttachFile(String dirName, List<Article> articleList) throws IOException {
        File file = new File(dirName);
        if (!file.exists()) {
            file.createNewFile();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Article article : articleList) {
                writer.append("id:").append(String.valueOf(article.getId())).append("\r\n")
                        .append("author: ").append(article.getAuthor()).append("\r\n")
                        .append("content: ").append(article.getContent()).append("\r\n");
            }
            writer.flush();
        }
        return file;
    }

    public static void putByteDataToStream(byte[] attach, ZipOutputStream zip, String fileName) throws IOException {
        ZipEntry zipEntry = new ZipEntry("zip" + File.separator + fileName);
        zip.putNextEntry(zipEntry);
        InputStream inputStream = new ByteArrayInputStream(attach);
        OutputStream out = new BufferedOutputStream(zip);
        write(inputStream, out);
        zip.closeEntry();
        zip.close();
    }

    public static void write(InputStream in, OutputStream out) throws IOException {
        byte[] data = new byte[4096];
        int len;
        while ((len = in.read(data)) != -1) {
            out.write(data, 0, len);
        }
        out.flush();
        in.close();
    }
}