package aizulove.com.fxxt.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 涓婁紶鏂囦欢
 */
public class FormFile {
    /* 涓婁紶鏂囦欢鐨勬暟鎹�*/
    private byte[] data;
    private InputStream inStream;
    private File file;
    /* 鏂囦欢鍚嶇О */
    private String filname;
    /* 璇锋眰鍙傛暟鍚嶇О*/
    private String parameterName;
    /* 鍐呭绫诲瀷 */
    private String contentType = "application/octet-stream";
    
    public FormFile(String filname, byte[] data, String parameterName, String contentType) {
        this.data = data;
        this.filname = filname;
        this.parameterName = parameterName;
        if(contentType!=null) this.contentType = contentType;
    }
    
    public FormFile(String filname, File file, String parameterName, String contentType) {
        this.filname = filname;
        this.parameterName = parameterName;
        this.file = file;
        try {
            this.inStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(contentType!=null) this.contentType = contentType;
    }
    
    public File getFile() {
        return file;
    }

    public InputStream getInStream() {
        return inStream;
    }

    public byte[] getData() {
        return data;
    }

    public String getFilname() {
        return filname;
    }

    public void setFilname(String filname) {
        this.filname = filname;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
}
