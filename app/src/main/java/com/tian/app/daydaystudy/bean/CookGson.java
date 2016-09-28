package com.tian.app.daydaystudy.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jiujiu on 2016/9/22.
 */
public class CookGson {
    @SerializedName("resultcode")
    private String resultcode;
    @SerializedName("reason")
    private String reason;
    @SerializedName("result")
    private List<Cook> result;

    public static class Cook {
        @SerializedName("parentId")
        private String parentId;
        @SerializedName("name")
        private String name;
        @SerializedName("list")
        private List<CookDetail> list;

        public static class CookDetail {
            @SerializedName("id")
            private String id;
            @SerializedName("name")
            private String name;
            @SerializedName("parentId")
            private String parentId;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<CookDetail> getList() {
            return list;
        }

        public void setList(List<CookDetail> list) {
            this.list = list;
        }
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<Cook> getResult() {
        return result;
    }

    public void setResult(List<Cook> result) {
        this.result = result;
    }
}
