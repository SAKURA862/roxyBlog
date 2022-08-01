package com.roxy.blog.utils;

import net.jpountz.lz4.*;
import org.xerial.snappy.Snappy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class CompressUtils {
    public static String deflateCompress(String s) {
        byte[] input = s.getBytes();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Deflater compressor = new Deflater(9);
        try {
            compressor.setInput(input);
            compressor.finish();
            final byte[] buf = new byte[2048];
            while (!compressor.finished()) {
                int count = compressor.deflate(buf);
                bos.write(buf, 0, count);
            }
        } finally {
            compressor.end();
        }
        return Base64.getEncoder().encodeToString(bos.toByteArray());
    }

    public static String deflateUncompress(String s) {
        byte[] input = Base64.getDecoder().decode(s);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Inflater decompressor = new Inflater();
        try {
            decompressor.setInput(input);
            final byte[] buf = new byte[2048];
            while (!decompressor.finished()) {
                int count = decompressor.inflate(buf);
                bos.write(buf, 0, count);
            }
        } catch (DataFormatException e) {
            throw new RuntimeException(e);
        } finally {
            decompressor.end();
        }
        return bos.toString();
    }

    public static String lz4Compress(String s) {
        byte[] srcBytes = s.getBytes();
        LZ4Factory factory = LZ4Factory.fastestInstance();
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        LZ4Compressor compressor = factory.fastCompressor();
        LZ4BlockOutputStream compressedOutput = new LZ4BlockOutputStream(byteOutput, 2048, compressor);
        try {
            compressedOutput.write(srcBytes);
            compressedOutput.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(byteOutput.toByteArray());
    }

    public static String lz4Uncompress(String s) {
        byte[] bytes = Base64.getDecoder().decode(s);
        LZ4Factory factory = LZ4Factory.fastestInstance();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        LZ4FastDecompressor decompressor = factory.fastDecompressor();
        LZ4BlockInputStream lzis = new LZ4BlockInputStream(new ByteArrayInputStream(bytes), decompressor);
        int count;
        byte[] buffer = new byte[2048];
        try {
            while  ((count = lzis.read(buffer)) != -1)  {
                baos.write(buffer, 0, count);
            }
            lzis.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return baos.toString();
    }

    public static String snappyCompress(String s) {
        byte[] srcBytes = s.getBytes(StandardCharsets.UTF_8);
        try {
            return Base64.getEncoder().encodeToString(Snappy.compress(srcBytes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String snappyUncompress(String s) {
        byte[] bytes = Base64.getDecoder().decode(s);
        try {
            return new String(Snappy.uncompress(bytes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
