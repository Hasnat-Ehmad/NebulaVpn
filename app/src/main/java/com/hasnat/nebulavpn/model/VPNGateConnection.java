package com.hasnat.nebulavpn.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import com.hasnat.nebulavpn.GlobalApp;
import com.hasnat.nebulavpn.R;
import com.hasnat.nebulavpn.utils.DataUtil;

import java.text.DecimalFormat;

public class VPNGateConnection implements Parcelable {
    public static final Creator<VPNGateConnection> CREATOR
            = new Creator<VPNGateConnection>() {
        public VPNGateConnection createFromParcel(Parcel in) {
            return new VPNGateConnection(in);
        }

        public VPNGateConnection[] newArray(int size) {
            return new VPNGateConnection[size];
        }
    };
    //HostName,IP,Score,Ping,Speed,CountryLong,CountryShort,NumVpnSessions,Uptime,TotalUsers,TotalTraffic,logType,Operator,Message,OpenVPN_ConfigData_Base64
    private String hostName;
    private String ip;
    private int score;
    private int ping;
    private int speed;
    private String countryLong;
    private String countryShort;
    private int numVpnSession;
    private long uptime;
    private int totalUser;
    private long totalTraffic;
    private String logType;
    private String operator;
    private String message;
    private String openVpnConfigData;
    private int tcpPort;
    private int udpPort;
    private int isL2TPSupport;

    private VPNGateConnection(Parcel in) {
        hostName = in.readString();
        ip = in.readString();
        score = in.readInt();
        ping = in.readInt();
        speed = in.readInt();
        countryLong = in.readString();
        countryShort = in.readString();
        numVpnSession = in.readInt();
        uptime = in.readLong();
        totalUser = in.readInt();
        totalTraffic = in.readLong();
        logType = in.readString();
        operator = in.readString();
        message = in.readString();
        openVpnConfigData = in.readString();
        tcpPort = in.readInt();
        udpPort = in.readInt();
        isL2TPSupport = in.readInt();
    }

    //Empty constructor
    private VPNGateConnection() {

    }

    public static VPNGateConnection fromCsv(String csvLine) {
        String[] properties = csvLine.split(",");
        try {
            int index = 0;
            VPNGateConnection vpnGateConnection = new VPNGateConnection();
            vpnGateConnection.hostName = properties[index++];
            vpnGateConnection.ip = properties[index++];
            vpnGateConnection.score = Integer.parseInt(properties[index++]);
            vpnGateConnection.ping = Integer.parseInt(properties[index++]);
            vpnGateConnection.speed = Integer.parseInt(properties[index++]);
            vpnGateConnection.countryLong = properties[index++];
            vpnGateConnection.countryShort = properties[index++];
            vpnGateConnection.numVpnSession = Integer.parseInt(properties[index++]);
            vpnGateConnection.uptime = Long.parseLong(properties[index++]);
            vpnGateConnection.totalUser = Integer.parseInt(properties[index++]);
            vpnGateConnection.totalTraffic = Long.parseLong(properties[index++]);
            vpnGateConnection.logType = properties[index++];
            vpnGateConnection.setOperator(properties[index++]);
            vpnGateConnection.message = properties[index++];
            vpnGateConnection.setOpenVpnConfigData(properties[index]);
            if (GlobalApp.getInstance().getDataUtil().getBooleanSetting(DataUtil.INCLUDE_UDP_SERVER, true) && properties.length >= index + 2) {
                vpnGateConnection.tcpPort = Integer.parseInt(properties[++index]);
                vpnGateConnection.udpPort = Integer.parseInt(properties[++index]);
                if (properties.length >= index + 1) {
                    vpnGateConnection.isL2TPSupport = Integer.parseInt(properties[++index]);
                }
            } else {
                vpnGateConnection.tcpPort = 0;
                vpnGateConnection.udpPort = 0;
                vpnGateConnection.isL2TPSupport = 0;
            }
            return vpnGateConnection;
        } catch (Exception e) {
            return null;
        }
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(hostName);
        out.writeString(ip);
        out.writeInt(score);
        out.writeInt(ping);
        out.writeInt(speed);
        out.writeString(countryLong);
        out.writeString(countryShort);
        out.writeInt(numVpnSession);
        out.writeLong(uptime);
        out.writeInt(totalUser);
        out.writeLong(totalTraffic);
        out.writeString(logType);
        out.writeString(operator);
        out.writeString(message);
        out.writeString(openVpnConfigData);
        out.writeInt(tcpPort);
        out.writeInt(udpPort);
        out.writeInt(isL2TPSupport);
    }

    private String decodeBase64(String base64str) {
        try {
            byte[] plainBytes = Base64.decode(base64str, 1);
            return new String(plainBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getCalculateHostName() {
        return hostName + ".opengw.net";
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getScoreAsString() {
        return String.valueOf(score);
    }

    public int getPing() {
        return ping;
    }

    public void setPing(int ping) {
        this.ping = ping;
    }

    public String getPingAsString() {
        return ping + "";
    }

    public String getCountryLong() {
        return countryLong;
    }

    public void setCountryLong(String countryLong) {
        this.countryLong = countryLong;
    }

    public String getCountryShort() {
        return countryShort;
    }

    public void setCountryShort(String countryShort) {
        this.countryShort = countryShort;
    }

    public int getNumVpnSession() {
        return numVpnSession;
    }

    public void setNumVpnSession(int numVpnSession) {
        this.numVpnSession = numVpnSession;
    }

    public String getNumVpnSessionAsString() {
        return numVpnSession + "";
    }

    public int getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(int totalUser) {
        this.totalUser = totalUser;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        operator = operator.replace("'s owner", "");
        this.operator = operator;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOpenVpnConfigData() {
        String openVpnConfigDataTmp = openVpnConfigData;
        if (GlobalApp.getInstance().getDataUtil().getBooleanSetting(DataUtil.USE_DOMAIN_TO_CONNECT, false)) {
            String dDomain = hostName + ".opengw.net";
            openVpnConfigDataTmp = openVpnConfigDataTmp.replace(ip, dDomain);

        }
        return openVpnConfigDataTmp;
    }

    public String getOpenVpnConfigDataUdp() {
        String openVpnConfigDataUdp = openVpnConfigData;
        if (this.tcpPort > 0) {
            // Current config is config for tcp need for udp
            openVpnConfigDataUdp = openVpnConfigDataUdp
                    .replace("proto tcp", "proto udp")
                    .replace("remote " + ip + " " + tcpPort, "remote " + ip + " " + udpPort);
        }
        if (GlobalApp.getInstance().getDataUtil().getBooleanSetting(DataUtil.USE_DOMAIN_TO_CONNECT, false)) {
            String dDomain = hostName + ".opengw.net";
            openVpnConfigDataUdp = openVpnConfigDataUdp.replace(ip, dDomain);

        }
        // Current config is udp only
        return openVpnConfigDataUdp;
    }

    public void setOpenVpnConfigData(String openVpnConfigData) {
        this.openVpnConfigData = decodeBase64(openVpnConfigData);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getCalculateSpeed() {
        return round((double) speed / (1000 * 1000));
    }

    public long getUptime() {
        return uptime;
    }

    public void setUptime(long uptime) {
        this.uptime = uptime;
    }

    public long getTotalTraffic() {
        return totalTraffic;
    }

    public void setTotalTraffic(long totalTraffic) {
        this.totalTraffic = totalTraffic;
    }

    public String getCalculateTotalTraffic() {
        double inMB = (double) totalTraffic / (1000 * 1000);
        if (inMB < 1000) {
            return round(inMB) + " MB";
        }
        double inGB = inMB / 1000;
        if (inGB < 1000) {
            return round(inMB / 1000) + " GB";
        }
        return round(inGB / 1000) + " TB";
    }

    public String getCalculateUpTime(Context context) {
        //Display as second
        if (uptime < 60000) {
            return round(uptime / 1000) + " " + context.getResources().getString(R.string.seconds);
        }
        //Display as minute
        if (uptime < 3600000) {
            return Math.round((double) uptime / 60000) + " " + context.getResources().getString(R.string.minutes);
        }
        //Display as hours
        if (uptime < 3600000 * 24) {
            return round((double) uptime / 3600000) + " " + context.getResources().getString(R.string.hours);
        }
        return round((double) uptime / (24 * 3600000)) + " " + context.getResources().getString(R.string.days);
    }

    private String round(double value) {
        DecimalFormat df = new DecimalFormat("####0.###");
        return df.format(value);
    }

    public int describeContents() {
        return 0;
    }

    public String getName() {
        return this.getName(false);
    }

    public String getName(boolean useUdp) {
        String address = ip;
        if (GlobalApp.getInstance().getDataUtil().getBooleanSetting(DataUtil.USE_DOMAIN_TO_CONNECT, false)) {
            address = hostName + ".opengw.net";
        }
        if (GlobalApp.getInstance().getDataUtil().getBooleanSetting(DataUtil.INCLUDE_UDP_SERVER, true)) {
            if (tcpPort == 0 && udpPort == 0) {
                // Current profile from non udp but open status page with include udp option
                return String.format("%s[%s]", countryLong, address);
            }
            return String.format("%s[%s][%s]", countryLong, address, useUdp || tcpPort == 0 ? "UDP:" + udpPort : "TCP:" + tcpPort);
        }
        return String.format("%s[%s]", countryLong, address);
    }

    public int getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(int tcpPort) {
        this.tcpPort = tcpPort;
    }

    public int getUdpPort() {
        return udpPort;
    }

    public void setUdpPort(int udpPort) {
        this.udpPort = udpPort;
    }

    public boolean isL2TPSupport() {
        return isL2TPSupport == 1;
    }

}
