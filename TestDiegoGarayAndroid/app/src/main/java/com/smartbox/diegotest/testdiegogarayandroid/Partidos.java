package com.smartbox.diegotest.testdiegogarayandroid;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Partidos implements Serializable{

        @SerializedName("homeScore")
        private String homeScore;
        @SerializedName("awayScore")
        private String awayScore;
        @SerializedName("homeTeam")
        private String homeTeam;
        @SerializedName("awayTeam")
        private String awayTeam;
        @SerializedName("eventStatus")
        private String eventStatus;
        @SerializedName("startDate")
        private String startDate;

        public Partidos(){

        }

        public String getHomeScore() {
                return homeScore;
        }

        public void setHomeScore(String homeScore) {
                this.homeScore = homeScore;
        }

        public String getAwayScore() {
                return awayScore;
        }

        public void setAwayScore(String awayScore) {
                this.awayScore = awayScore;
        }

        public String getHomeTeam() {
                return homeTeam;
        }

        public void setHomeTeam(String homeTeam) {
                this.homeTeam = homeTeam;
        }

        public String getAwayTeam() {
                return awayTeam;
        }

        public void setAwayTeam(String awayTeam) {
                this.awayTeam = awayTeam;
        }

        public String getEventStatus() {
                return eventStatus;
        }

        public void setEventStatus(String eventStatus) {
                this.eventStatus = eventStatus;
        }

        public String getStartDate() {
                return startDate;
        }

        public void setStartDate(String startDate) {
                this.startDate = startDate;
        }

        @Override
        public String toString() {
                return "Partidos{" +
                        "homeScore='" + homeScore + '\'' +
                        ", awayScore='" + awayScore + '\'' +
                        ", homeTeam='" + homeTeam + '\'' +
                        ", awayTeam='" + awayTeam + '\'' +
                        ", eventStatus='" + eventStatus + '\'' +
                        ", startDate='" + startDate + '\'' +
                        '}';
        }
}
