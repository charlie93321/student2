package com.spring.zk.topic;

import lombok.Data;

import javax.jms.Destination;

@Data
public  class MqConfiguration{
        private String userName;
        private String password;
        private String url;
        private boolean transacted;
        private int acknowledgeMode;
        private String destination;




        public MqConfiguration() {
        }

        public MqConfiguration(String userName, String password, String url, boolean transacted, int acknowledgeMode) {
            this.userName = userName;
            this.password = password;
            this.url = url;
            this.transacted = transacted;
            this.acknowledgeMode = acknowledgeMode;
        }
    }