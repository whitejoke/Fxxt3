package aizulove.com.fxxt.modle.entity;

import java.util.List;

/**
 * Created by joker on 2016/8/19.
 */
public class Pinglun {

    /**
     * itemid : 39
     * mallid : 810
     * buyer : lc2016
     * seller : admin
     * buyer_star : true
     * buyer_comment : 感谢您对蓝车世界的支持！
     * buyer_ctime : 1460442421
     * buyer_reply :
     * buyer_rtime : 0
     * seller_star : true
     * seller_comment : 为了买这俩自行车，和客服聊了一个礼拜。在线客服认真负责，值得信任。第二天就到了，仔细检查之后，自行车完好无损，非常满意。
     * seller_ctime : 1460362155
     * seller_reply :
     * seller_rtime : 0
     */
        private int itemid;
        private int mallid;
        private String buyer;
        private String seller;
        private boolean buyer_star;
        private String buyer_comment;
        private int buyer_ctime;
        private String buyer_reply;
        private int buyer_rtime;
        private boolean seller_star;
        private String seller_comment;

        public int getItemid() {
            return itemid;
        }

        public void setItemid(int itemid) {
            this.itemid = itemid;
        }

        public int getMallid() {
            return mallid;
        }

        public void setMallid(int mallid) {
            this.mallid = mallid;
        }

        public String getBuyer() {
            return buyer;
        }

        public void setBuyer(String buyer) {
            this.buyer = buyer;
        }

        public String getSeller() {
            return seller;
        }

        public void setSeller(String seller) {
            this.seller = seller;
        }

        public boolean isBuyer_star() {
            return buyer_star;
        }

        public void setBuyer_star(boolean buyer_star) {
            this.buyer_star = buyer_star;
        }

        public String getBuyer_comment() {
            return buyer_comment;
        }

        public void setBuyer_comment(String buyer_comment) {
            this.buyer_comment = buyer_comment;
        }

        public int getBuyer_ctime() {
            return buyer_ctime;
        }

        public void setBuyer_ctime(int buyer_ctime) {
            this.buyer_ctime = buyer_ctime;
        }

        public String getBuyer_reply() {
            return buyer_reply;
        }

        public void setBuyer_reply(String buyer_reply) {
            this.buyer_reply = buyer_reply;
        }

        public int getBuyer_rtime() {
            return buyer_rtime;
        }

        public void setBuyer_rtime(int buyer_rtime) {
            this.buyer_rtime = buyer_rtime;
        }

        public boolean isSeller_star() {
            return seller_star;
        }

        public void setSeller_star(boolean seller_star) {
            this.seller_star = seller_star;
        }

        public String getSeller_comment() {
            return seller_comment;
        }

        public void setSeller_comment(String seller_comment) {
            this.seller_comment = seller_comment;
        }
    }

