package com.my_ip.util.file;

import java.io.*;

public class FileUtil {

    private static final String UTF_8 = "UTF-8";

    private static final String Unicode = "Unicode";

    private static final String UTF_16BE = "UTF-16BE";

    private static final String ANSI_ASCII = "ANSI|ASCII";

    private static final String GBK = "GBK";



    /**
     * 自动根据文件编码格式读取文件
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static BufferedReader readFile(String filePath) throws Exception {
        return new BufferedReader(new InputStreamReader(new FileInputStream(filePath), codeString(filePath)));
    }

    public static BufferedReader readFile(InputStream is) throws Exception {
        // 如果你需要自动检测编码，可以参考 codeString 方法的逻辑，
        // 但由于 InputStream 没有文件名，可能需要通过其它方式确定编码。
        // 如果确定 CSV 文件是 UTF-8 编码的话，可以直接写 "UTF-8"
        return new BufferedReader(new InputStreamReader(is, "UTF-8"));
    }

    public static String codeString(String fileName) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(
                new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();
        String code;
        //其中的 0xefbb、0xfffe、0xfeff、0x5c75这些都是这个文件的前面两个字节的16进制数
        switch (p) {
            case 0xefbb:
                code = UTF_8;
                break;
            case 0xfffe:
                code = Unicode;
                break;
            case 0xfeff:
                code = UTF_16BE;
                break;
            case 0x5c75:
                code = ANSI_ASCII;
                break;
            default:
                code = GBK;
        }

        return code;
    }

}
