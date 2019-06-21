package com.example.httplibrary.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/16.
 */

public class BannerBean {


    /**
     * timestamp : 1536050908131
     * status : 0
     * data : {"banners":[{"bannerType":"1","bannerItemId":"df697b2bd2d34feb962c6ce1a60bcce8","bannerTitle":"精品书籍免费阅读","bannerImgUrl":"https://eximages.12306.cn/wifiFileBucket/uploadFiles/20180904/b15c71bda0d449c38972b3b9a3142a74.jpg","bannerTurnUrl":"https://eximages.12306.cn/wificloud/resources/public/pages/default_12306sh_image/novel_adv.html"}],"adPostion":[{"advertPositionId":"0033","advertType":"banner","advertPositionInfo":"WiFi首页","advertOrder":1}]}
     */

    private String timestamp;
    private int status;
    private DataBean data;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<BannersBean> banners;
        private List<AdPostionBean> adPostion;

        public List<BannersBean> getBanners() {
            return banners;
        }

        public void setBanners(List<BannersBean> banners) {
            this.banners = banners;
        }

        public List<AdPostionBean> getAdPostion() {
            return adPostion;
        }

        public void setAdPostion(List<AdPostionBean> adPostion) {
            this.adPostion = adPostion;
        }

        public static class BannersBean {
            /**
             * bannerType : 1
             * bannerItemId : df697b2bd2d34feb962c6ce1a60bcce8
             * bannerTitle : 精品书籍免费阅读
             * bannerImgUrl : https://eximages.12306.cn/wifiFileBucket/uploadFiles/20180904/b15c71bda0d449c38972b3b9a3142a74.jpg
             * bannerTurnUrl : https://eximages.12306.cn/wificloud/resources/public/pages/default_12306sh_image/novel_adv.html
             */

            private String bannerType;
            private String bannerItemId;
            private String bannerTitle;
            private String bannerImgUrl;
            private String bannerTurnUrl;

            public String getBannerType() {
                return bannerType;
            }

            public void setBannerType(String bannerType) {
                this.bannerType = bannerType;
            }

            public String getBannerItemId() {
                return bannerItemId;
            }

            public void setBannerItemId(String bannerItemId) {
                this.bannerItemId = bannerItemId;
            }

            public String getBannerTitle() {
                return bannerTitle;
            }

            public void setBannerTitle(String bannerTitle) {
                this.bannerTitle = bannerTitle;
            }

            public String getBannerImgUrl() {
                return bannerImgUrl;
            }

            public void setBannerImgUrl(String bannerImgUrl) {
                this.bannerImgUrl = bannerImgUrl;
            }

            public String getBannerTurnUrl() {
                return bannerTurnUrl;
            }

            public void setBannerTurnUrl(String bannerTurnUrl) {
                this.bannerTurnUrl = bannerTurnUrl;
            }
        }

        public static class AdPostionBean {
            /**
             * advertPositionId : 0033
             * advertType : banner
             * advertPositionInfo : WiFi首页
             * advertOrder : 1
             */

            private String advertPositionId;
            private String advertType;
            private String advertPositionInfo;
            private int advertOrder;

            public String getAdvertPositionId() {
                return advertPositionId;
            }

            public void setAdvertPositionId(String advertPositionId) {
                this.advertPositionId = advertPositionId;
            }

            public String getAdvertType() {
                return advertType;
            }

            public void setAdvertType(String advertType) {
                this.advertType = advertType;
            }

            public String getAdvertPositionInfo() {
                return advertPositionInfo;
            }

            public void setAdvertPositionInfo(String advertPositionInfo) {
                this.advertPositionInfo = advertPositionInfo;
            }

            public int getAdvertOrder() {
                return advertOrder;
            }

            public void setAdvertOrder(int advertOrder) {
                this.advertOrder = advertOrder;
            }
        }
    }
}
