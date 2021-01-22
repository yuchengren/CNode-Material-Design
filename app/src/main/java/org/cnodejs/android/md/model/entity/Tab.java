package org.cnodejs.android.md.model.entity;

import android.support.annotation.StringRes;

import org.cnodejs.android.md.BuildConfig;
import org.cnodejs.android.md.R;

import java.util.ArrayList;
import java.util.List;

public enum Tab {

    all(R.string.app_name),
    good(R.string.tab_good),

    common(R.string.tab_common),
    share(R.string.tab_share),
    trade(R.string.tab_trade),
    fun(R.string.tab_fun),
    job(R.string.tab_job),
    advertising(R.string.tab_advertising),
    notice(R.string.tab_notice),

    dev(R.string.tab_dev),
    unknown(R.string.tab_unknown);

    @StringRes
    private final int nameId;

    Tab(@StringRes int nameId) {
        this.nameId = nameId;
    }

    @StringRes
    public int getNameId() {
        return nameId;
    }

    public static List<Tab> getPublishableTabList() {
        List<Tab> tabList = new ArrayList<>();

        tabList.add(common);
        tabList.add(share);
        tabList.add(trade);
        tabList.add(fun);
        tabList.add(job);
        tabList.add(advertising);
        tabList.add(notice);
        if (BuildConfig.DEBUG) {
            tabList.add(dev);
        }
        return tabList;
    }

}
