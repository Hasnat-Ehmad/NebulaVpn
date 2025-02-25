package com.hasnat.nebulavpn.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class VPNGateConnectionList implements Parcelable {
    public static final Creator<VPNGateConnectionList> CREATOR
            = new Creator<VPNGateConnectionList>() {
        public VPNGateConnectionList createFromParcel(Parcel in) {
            return new VPNGateConnectionList(in);
        }

        public VPNGateConnectionList[] newArray(int size) {
            return new VPNGateConnectionList[size];
        }
    };
    private List<VPNGateConnection> data;

    public VPNGateConnectionList() {
        data = new ArrayList<>();
    }

    private VPNGateConnectionList(Parcel in) {
        data = in.createTypedArrayList(VPNGateConnection.CREATOR);
    }

    public List<VPNGateConnection> getAll() {
        return data;
    }

    /**
     * Filter connection by keyword
     *
     * @param keyword keyword to filter
     * @return
     */
    public VPNGateConnectionList filter(String keyword) {
        VPNGateConnectionList result = new VPNGateConnectionList();
        for (VPNGateConnection vpnGateConnection : data) {
            if (vpnGateConnection.getCountryLong().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(vpnGateConnection);
            }
        }
        return result;
    }

    public void add(VPNGateConnection vpnGateConnection) {
        data.add(vpnGateConnection);
    }

    public void remove(int index) {
        data.remove(index);
    }

    public void clear() {
        data.clear();
    }

    public void addAll(VPNGateConnectionList list) {
        data.addAll(list.data);
    }

    public VPNGateConnection get(int index) {
        return data.get(index);
    }

    public int size() {
        return data.size();
    }

    /**
     * Get ordered list
     *
     * @param property
     * @param type     order type 0 = ASC, 1 = DESC
     * @return
     */
    public void sort(final String property, final int type) {
        Collections.sort(data, new Comparator<VPNGateConnection>() {
            @Override
            public int compare(VPNGateConnection o1, VPNGateConnection o2) {
                if (type == ORDER.ASC) {
                    switch (property) {
                        case SortProperty.COUNTRY:
                            return o1.getCountryLong().compareTo(o2.getCountryLong());
                        case SortProperty.SPEED:
                            return Integer.valueOf(o1.getSpeed()).compareTo(o2.getSpeed());
                        case SortProperty.PING:
                            return Integer.valueOf(o1.getPing()).compareTo(o2.getPing());
                        case SortProperty.SCORE:
                            return Integer.valueOf(o1.getScore()).compareTo(o2.getScore());
                        case SortProperty.UPTIME:
                            return Long.valueOf(o1.getUptime()).compareTo(o2.getUptime());
                        case SortProperty.SESSION:
                            return Integer.valueOf(o1.getNumVpnSession()).compareTo(o2.getNumVpnSession());
                        default:
                            return 0;
                    }
                } else if (type == ORDER.DESC) {
                    switch (property) {
                        case SortProperty.COUNTRY:
                            return o2.getCountryLong().compareTo(o1.getCountryLong());
                        case SortProperty.SPEED:
                            return Integer.valueOf(o2.getSpeed()).compareTo(o1.getSpeed());
                        case SortProperty.PING:
                            return Integer.valueOf(o2.getPing()).compareTo(o1.getPing());
                        case SortProperty.SCORE:
                            return Integer.valueOf(o2.getScore()).compareTo(o1.getScore());
                        case SortProperty.UPTIME:
                            return Long.valueOf(o2.getUptime()).compareTo(o1.getUptime());
                        case SortProperty.SESSION:
                            return Integer.valueOf(o2.getNumVpnSession()).compareTo(o1.getNumVpnSession());
                        default:
                            return 0;
                    }
                }
                return 0;
            }
        });
    }

    public List<VPNGateConnection> getData() {
        return data;
    }

    public void setData(List<VPNGateConnection> data) {
        this.data = data;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeTypedList(data);
    }

    public final class ORDER {
        public static final int ASC = 0;
        public static final int DESC = 1;
    }

    public final class SortProperty {
        public static final String COUNTRY = "COUNTRY";
        public static final String SPEED = "SPEED";
        public static final String PING = "PING";
        public static final String SCORE = "SCORE";
        public static final String UPTIME = "UPTIME";
        public static final String SESSION = "SESSION";
    }
}
