package com.example.springboot.test;

/**
 * 文件内容
 *
 * @author xingce
 * @date 2021/01/07 10:06
 */
public class FileContent {
    private String id;
    private String groupId;
    private Float quota;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Float getQuota() {
        return quota;
    }

    public void setQuota(Float quota) {
        this.quota = quota;
    }

    @Override
    public String toString() {
        return groupId + "," + id + "," + quota;
    }
}
