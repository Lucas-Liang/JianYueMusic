package exmple.com.jianyuemusic.Pojo;

import java.io.Serializable;

/**
 * @Author: LKL
 * @Date: 2018/6/11 14:14
 * @CodeInfo:文件实体类
 */
public class FileInfo implements Serializable{
    private  int id;
    private String url;
    private String fileName;
    private String fileSinger;
    private long length;
    private long finish;

    public FileInfo() {
    }

    public FileInfo(int id, String url, String fileName, String fileSinger, long length, long finish) {
        this.id = id;
        this.url = url;
        this.fileName = fileName;
        this.fileSinger = fileSinger;
        this.length = length;
        this.finish = finish;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileSinger='" + fileSinger + '\'' +
                ", length=" + length +
                ", finish=" + finish +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getFinish() {
        return finish;
    }

    public void setFinish(long finish) {
        this.finish = finish;
    }

    public String getFileSinger() {
        return fileSinger;
    }

    public void setFileSinger(String fileSinger) {
        this.fileSinger = fileSinger;
    }
}
