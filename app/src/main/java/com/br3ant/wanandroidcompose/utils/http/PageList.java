package com.br3ant.wanandroidcompose.utils.http;

import java.util.ArrayList;
import java.util.List;

/**
 * @author houqiqi on 2022/6/15
 */
public class PageList<T> {

    private int curPage; //当前页数
    private boolean over; // 是否结束
    private int size; //
    private List<T> datas;

    public int getCurPage() {
        return curPage;
    }

    public boolean getOver() {
        return over;
    }

    public int getSize() {
        return size;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setDatas(ArrayList<T> datas) {
        this.datas = datas;
    }
}
