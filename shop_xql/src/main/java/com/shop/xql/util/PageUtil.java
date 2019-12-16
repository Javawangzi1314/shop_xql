package com.shop.xql.util;

import java.io.Serializable;

/**
 * @Auther: Java小王子皮皮磊
 * @Date: 2019/12/13
 * @Description: com.shop.xql.util
 * @version: 1.0
 */
public class PageUtil implements Serializable {
    public Integer currentPageNum;// 当前页
    private Integer pageSize = 4;// 每页显示的条数
    private Integer totalRecords;// 总记录条数
    private Integer startIndex;// 查询的开始记录索引
    private Integer endIndex;//查询的结束记录索引
    private Integer totalPageNum;// 总页数
    private Integer prePageNum;// 上一页
    private Integer nextPageNum;// 下一页
    // 用于显示页码的属性。我们的需求是页面上最多只显示9个页码。当前页在允许的情况下永远居中
    private Integer beginPageNum;
    private Integer endPageNum;

    /**
     * 定义程序序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * 要想使用此类，必须提供2个参数
     *
     * @param currentPageNum
     *            当前页
     * @param totalRecords
     *            总记录条数
     */
    public PageUtil(int currentPageNum, int totalRecords) {
        this.currentPageNum = currentPageNum;
        this.totalRecords = totalRecords;

        // 计算开始记录索引
        startIndex = (currentPageNum - 1) * pageSize;

        //计算结束索引
        endIndex = startIndex+pageSize > totalRecords ? totalRecords : startIndex+pageSize-1;

        // 计算总页数
        totalPageNum = totalRecords % pageSize == 0 ? totalRecords / pageSize
                : totalRecords / pageSize + 1;

        // 计算页号
        if (totalPageNum < 9) {
            beginPageNum = 1;
            endPageNum = totalPageNum;
        } else {
            beginPageNum = currentPageNum - 4;
            endPageNum = currentPageNum + 4;
            if (beginPageNum < 1) {
                beginPageNum = 1;
                endPageNum = beginPageNum + 8;
            }
            if (endPageNum > totalPageNum) {
                endPageNum = totalPageNum;
                beginPageNum = endPageNum - 8;
            }
        }

    }

    public int getPrePageNum() {
        // 计算上一页
        prePageNum = currentPageNum - 1;
        // 如果当前页是第一页，上一页还是第一页
        return prePageNum < 1 ? 1 : prePageNum;
    }

    public int getNextPageNum() {
        // 计算下一页
        nextPageNum = currentPageNum + 1;
        // 如果当前页是最后一页，下一页还是最后一页
        return nextPageNum > totalPageNum ? totalPageNum : nextPageNum;
    }

    public boolean hasFirst() { // 有首页
        return getCurrentPageNum() > 1; // 当前页面不是首页就有首页
    }

    public boolean hasLast() { // 有尾页
        return getCurrentPageNum() < getTotalPageNum(); // 当前页面小于最大页码数
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }
    public int getBeginPageNum() {
        return beginPageNum;
    }

    public void setBeginPageNum(int beginPageNum) {
        this.beginPageNum = beginPageNum;
    }

    public int getEndPageNum() {
        return endPageNum;
    }

    public void setEndPageNum(int endPageNum) {
        this.endPageNum = endPageNum;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void setPrePageNum(int prePageNum) {
        this.prePageNum = prePageNum;
    }

    public void setNextPageNum(int nextPageNum) {
        this.nextPageNum = nextPageNum;
    }
    public int getEndIndex() {
        return endIndex;
    }
    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    @Override
    public String toString() {
        return "PageUtil [currentPageNum=" + currentPageNum + ", pageSize="
                + pageSize + ", totalRecords=" + totalRecords + ", startIndex="
                + startIndex + ", endIndex=" + endIndex + ", totalPageNum="
                + totalPageNum + ", prePageNum=" + prePageNum
                + ", nextPageNum=" + nextPageNum + ", beginPageNum="
                + beginPageNum + ", endPageNum=" + endPageNum + "]";
    }


}