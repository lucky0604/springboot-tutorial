package com.lucky.blog.common.constant;

public class Constant {
    /**
     * 超级管理员ID
     */
    public static final int SUPER_ADMIN = 1;

    public enum UserStatus {
        /**
         * 正常
         */
        NORMAL("normal"),
        /**
         * 暂停
         */
        PAUSE("pause");

        private String status;

        UserStatus(String status) {
            this.status = status;
        }

        public String getValue() {
            return status;
        }
    }

    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     */
    public enum ScheduleStatus {
        NORMAL(0),
        PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum CloudService {
        QINIU(1),
        ALIYUN(2),
        QCLOUD(3);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
