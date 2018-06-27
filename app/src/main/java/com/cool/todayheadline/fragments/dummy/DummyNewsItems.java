package com.cool.todayheadline.fragments.dummy;

import com.cool.todayheadline.vo.NewsItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyNewsItems
{

    /**
     * An array of sample (dummy) items.
     */
    public static final List<NewsItem> ITEMS = new ArrayList<NewsItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, NewsItem> ITEM_MAP = new HashMap<String, NewsItem>();

    private static final int COUNT = 10;

    static
    {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++)
        {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(NewsItem item)
    {
        ITEMS.add(item);
    }

    private static NewsItem createDummyItem(int position)
    {
        NewsItem newsItem = new NewsItem(position+"","社会"+position,"http://06.imgmini.eastday.com/mobile/20180627/20180627094810_268f4f177b506235815e72ce99add15d_1_mwpm_03200403.jpg",
                "新闻"+position,"假人工厂","2018-06-27 09:46");
        return newsItem;
    }

}
