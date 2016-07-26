package com.xdx.dllo.beautygoodsdemo.stylist.stylistinto.stylistinfoworks.worksdetails;

import java.util.List;

/**
 * Created by dllo on 16/7/25.
 */
public class WorksDetailsBean {


    /**
     * unlike_user_num : 7
     * designer : {"city":"","concept":"我们想要做出在情感和科技上都有意义的产品","name":"Jen Rubio & Stephanie Korey ","label":"Away 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/6/13/228014e9-d396-40c9-b4ee-62285aabe544.jpg","id":21}
     * name : The Carry-On
     * cover_images : []
     * is_marked : 0
     * like_type : -1
     * shop_urls : [{"shop_url":"https://www.awaytravel.com/buy","shop_name":"AWAY 官方网站"}]
     * refer_articles : [{"image_url":"http://dstatic.zuimeia.com/common/image/2016/7/21/986e346a-f59b-416a-b758-a02a6c891cca_750x750.jpeg","sub_title":"AWAY travel：一只让你随时离开的旅行箱","id":50,"title":"一只旅行箱，如何装下你的百年孤独？"}]
     * like_user_num : 55
     * mark_user_num : 88
     * images : ["http://dstatic.zuimeia.com/product/pic/2016/6/12/a9ee47ec-6218-4cff-8f48-08e608abc2ff_1400x940.jpeg","http://dstatic.zuimeia.com/product/pic/2016/6/12/3b6dd82d-fd37-4fc1-b8ea-49bd6229eca9_833x1000.jpeg","http://dstatic.zuimeia.com/product/pic/2016/6/12/68892cf9-8ce0-43d2-a2b5-d3a0431201a8_833x1000.jpeg","http://dstatic.zuimeia.com/product/pic/2016/6/12/8f00428f-e0fb-4a79-9391-f70cfc693e17_832x1000.jpeg","http://dstatic.zuimeia.com/product/pic/2016/6/12/edd50e0d-2078-4480-8935-0f61c8d0afd3_833x1000.jpeg","http://dstatic.zuimeia.com/product/pic/2016/6/12/8ac137dd-6e4f-49ab-bac1-6162cf5be4cb_1273x1000.jpeg","http://dstatic.zuimeia.com/product/pic/2016/6/12/f22e3403-c928-4e7f-b7ee-ae1daa879e9b_1232x1000.jpeg","http://dstatic.zuimeia.com/product/pic/2016/6/12/2f3d74a0-f4b3-45ac-b984-e43bbcc1c886_1232x1000.jpeg","http://dstatic.zuimeia.com/product/pic/2016/6/12/a8da5a8a-5bd3-44ce-860d-4cbc8b4b640e_1232x1000.jpeg"]
     * id : 457
     * digest :
     * desc :
     */

    private DataBean data;
    /**
     * data : {"unlike_user_num":7,"designer":{"city":"","concept":"我们想要做出在情感和科技上都有意义的产品","name":"Jen Rubio & Stephanie Korey ","label":"Away 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/6/13/228014e9-d396-40c9-b4ee-62285aabe544.jpg","id":21},"name":"The Carry-On","cover_images":[],"is_marked":0,"like_type":-1,"shop_urls":[{"shop_url":"https://www.awaytravel.com/buy","shop_name":"AWAY 官方网站"}],"refer_articles":[{"image_url":"http://dstatic.zuimeia.com/common/image/2016/7/21/986e346a-f59b-416a-b758-a02a6c891cca_750x750.jpeg","sub_title":"AWAY travel：一只让你随时离开的旅行箱","id":50,"title":"一只旅行箱，如何装下你的百年孤独？"}],"like_user_num":55,"mark_user_num":88,"images":["http://dstatic.zuimeia.com/product/pic/2016/6/12/a9ee47ec-6218-4cff-8f48-08e608abc2ff_1400x940.jpeg","http://dstatic.zuimeia.com/product/pic/2016/6/12/3b6dd82d-fd37-4fc1-b8ea-49bd6229eca9_833x1000.jpeg","http://dstatic.zuimeia.com/product/pic/2016/6/12/68892cf9-8ce0-43d2-a2b5-d3a0431201a8_833x1000.jpeg","http://dstatic.zuimeia.com/product/pic/2016/6/12/8f00428f-e0fb-4a79-9391-f70cfc693e17_832x1000.jpeg","http://dstatic.zuimeia.com/product/pic/2016/6/12/edd50e0d-2078-4480-8935-0f61c8d0afd3_833x1000.jpeg","http://dstatic.zuimeia.com/product/pic/2016/6/12/8ac137dd-6e4f-49ab-bac1-6162cf5be4cb_1273x1000.jpeg","http://dstatic.zuimeia.com/product/pic/2016/6/12/f22e3403-c928-4e7f-b7ee-ae1daa879e9b_1232x1000.jpeg","http://dstatic.zuimeia.com/product/pic/2016/6/12/2f3d74a0-f4b3-45ac-b984-e43bbcc1c886_1232x1000.jpeg","http://dstatic.zuimeia.com/product/pic/2016/6/12/a8da5a8a-5bd3-44ce-860d-4cbc8b4b640e_1232x1000.jpeg"],"id":457,"digest":"","desc":""}
     * result : 1
     */

    private int result;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public static class DataBean {
        private int unlike_user_num;
        /**
         * city :
         * concept : 我们想要做出在情感和科技上都有意义的产品
         * name : Jen Rubio & Stephanie Korey
         * label : Away 创始人
         * avatar_url : http://dstatic.zuimeia.com/designer/avatar/2016/6/13/228014e9-d396-40c9-b4ee-62285aabe544.jpg
         * id : 21
         */

        private DesignerBean designer;
        private String name;
        private int is_marked;
        private int like_type;
        private int like_user_num;
        private int mark_user_num;
        private int id;
        private String digest;
        private String desc;
        private List<?> cover_images;
        /**
         * shop_url : https://www.awaytravel.com/buy
         * shop_name : AWAY 官方网站
         */

        private List<ShopUrlsBean> shop_urls;
        /**
         * image_url : http://dstatic.zuimeia.com/common/image/2016/7/21/986e346a-f59b-416a-b758-a02a6c891cca_750x750.jpeg
         * sub_title : AWAY travel：一只让你随时离开的旅行箱
         * id : 50
         * title : 一只旅行箱，如何装下你的百年孤独？
         */

        private List<ReferArticlesBean> refer_articles;
        private List<String> images;

        public int getUnlike_user_num() {
            return unlike_user_num;
        }

        public void setUnlike_user_num(int unlike_user_num) {
            this.unlike_user_num = unlike_user_num;
        }

        public DesignerBean getDesigner() {
            return designer;
        }

        public void setDesigner(DesignerBean designer) {
            this.designer = designer;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIs_marked() {
            return is_marked;
        }

        public void setIs_marked(int is_marked) {
            this.is_marked = is_marked;
        }

        public int getLike_type() {
            return like_type;
        }

        public void setLike_type(int like_type) {
            this.like_type = like_type;
        }

        public int getLike_user_num() {
            return like_user_num;
        }

        public void setLike_user_num(int like_user_num) {
            this.like_user_num = like_user_num;
        }

        public int getMark_user_num() {
            return mark_user_num;
        }

        public void setMark_user_num(int mark_user_num) {
            this.mark_user_num = mark_user_num;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public List<?> getCover_images() {
            return cover_images;
        }

        public void setCover_images(List<?> cover_images) {
            this.cover_images = cover_images;
        }

        public List<ShopUrlsBean> getShop_urls() {
            return shop_urls;
        }

        public void setShop_urls(List<ShopUrlsBean> shop_urls) {
            this.shop_urls = shop_urls;
        }

        public List<ReferArticlesBean> getRefer_articles() {
            return refer_articles;
        }

        public void setRefer_articles(List<ReferArticlesBean> refer_articles) {
            this.refer_articles = refer_articles;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public static class DesignerBean {
            private String city;
            private String concept;
            private String name;
            private String label;
            private String avatar_url;
            private int id;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getConcept() {
                return concept;
            }

            public void setConcept(String concept) {
                this.concept = concept;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }

        public static class ShopUrlsBean {
            private String shop_url;
            private String shop_name;

            public String getShop_url() {
                return shop_url;
            }

            public void setShop_url(String shop_url) {
                this.shop_url = shop_url;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }
        }

        public static class ReferArticlesBean {
            private String image_url;
            private String sub_title;
            private int id;
            private String title;

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getSub_title() {
                return sub_title;
            }

            public void setSub_title(String sub_title) {
                this.sub_title = sub_title;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
