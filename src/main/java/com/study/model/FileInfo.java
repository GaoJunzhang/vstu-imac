package com.study.model;

import javax.persistence.*;

@Table(name = "file_info")
public class FileInfo {
    private static final long serialVersionUID = -6812242071705361506L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String filename;

    private String fileaddress;

    /**
     * 设备描述
     */
    private String filedescription;

    private String fileimgsrc;

    private String createtime;

    private String enable;

    /**
     * 资源类型（0-应用，1-视频）
     */
    private String filetype;

    /**
     * 副标题
     */
    private String subtitle;

    /**
     * 学科
     */
    private String disciplines;

    /**
     * pdf
     */
    private String pdfurl;

    /**
     * app视频
     */
    private String appvideourl;

    public FileInfo() {
    }

    public FileInfo(Integer id, String filename, String fileaddress, String filedescription, String fileimgsrc, String createtime, String enable,
                    String filetype, String subtitle, String disciplines, String pdfurl, String appvideourl) {
        this.id=id;
        this.filename=filename;
        this.fileaddress=fileaddress;
        this.filedescription=filedescription;
        this.fileimgsrc=fileimgsrc;
        this.createtime=createtime;
        this.enable=enable;
        this.filetype=filetype;
        this.subtitle=subtitle;
        this.disciplines=disciplines;
        this.pdfurl=pdfurl;
        this.appvideourl=appvideourl;
    }
    @Column(name = "package_name")
    private String packageName;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return fileaddress
     */
    public String getFileaddress() {
        return fileaddress;
    }

    /**
     * @param fileaddress
     */
    public void setFileaddress(String fileaddress) {
        this.fileaddress = fileaddress;
    }

    /**
     * 获取设备描述
     *
     * @return filedescription - 设备描述
     */
    public String getFiledescription() {
        return filedescription;
    }

    /**
     * 设置设备描述
     *
     * @param filedescription 设备描述
     */
    public void setFiledescription(String filedescription) {
        this.filedescription = filedescription;
    }

    /**
     * @return fileimgsrc
     */
    public String getFileimgsrc() {
        return fileimgsrc;
    }

    /**
     * @param fileimgsrc
     */
    public void setFileimgsrc(String fileimgsrc) {
        this.fileimgsrc = fileimgsrc;
    }

    /**
     * @return createtime
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    /**
     * @return enable
     */
    public String getEnable() {
        return enable;
    }

    /**
     * @param enable
     */
    public void setEnable(String enable) {
        this.enable = enable;
    }

    /**
     * 获取资源类型（0-应用，1-视频）
     *
     * @return filetype - 资源类型（0-应用，1-视频）
     */
    public String getFiletype() {
        return filetype;
    }

    /**
     * 设置资源类型（0-应用，1-视频）
     *
     * @param filetype 资源类型（0-应用，1-视频）
     */
    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    /**
     * 获取副标题
     *
     * @return subtitle - 副标题
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * 设置副标题
     *
     * @param subtitle 副标题
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * 获取学科
     *
     * @return disciplines - 学科
     */
    public String getDisciplines() {
        return disciplines;
    }

    /**
     * 设置学科
     *
     * @param disciplines 学科
     */
    public void setDisciplines(String disciplines) {
        this.disciplines = disciplines;
    }

    /**
     * 获取pdf
     *
     * @return pdfurl - pdf
     */
    public String getPdfurl() {
        return pdfurl;
    }

    /**
     * 设置pdf
     *
     * @param pdfurl pdf
     */
    public void setPdfurl(String pdfurl) {
        this.pdfurl = pdfurl;
    }

    /**
     * 获取app视频
     *
     * @return appvideourl - app视频
     */
    public String getAppvideourl() {
        return appvideourl;
    }

    /**
     * 设置app视频
     *
     * @param appvideourl app视频
     */
    public void setAppvideourl(String appvideourl) {
        this.appvideourl = appvideourl;
    }

    /**
     * @return package_name
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * @param packageName
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}