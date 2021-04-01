package com.example.designnavigation.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.display.fitness.ui.designnavigation.R;
import com.example.designnavigation.adapter.ViewPagerAdapter;
import com.example.designnavigation.utils.NavigationUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;


/**
 * @auther: yees
 * @date: 2021/1/29
 */
public class NormalNavigationBar extends LinearLayout {


    private RelativeLayout AddContainerLayout;

    //Tab数量
    private int tabCount = 0;

    private LinearLayout navigationLayout;
    private RelativeLayout contentView;
    //分割线
    private View lineView;

    //红点集合
    private final List<View> hintPointList = new ArrayList<>();

    //消息数量集合
    private final List<TextView> msgPointList = new ArrayList<>();

    //底部Image集合
    private List<ImageView> imageViewList = new ArrayList<>();

    //底部Text集合
    private List<TextView> textViewList = new ArrayList<>();

    //底部TabLayout（除中间加号）
    private List<View> tabList = new ArrayList<>();

    private ViewPager mViewPager;


    //文字集合
    private String[] titleItems = new String[]{};
    //未选择 图片集合
    private int[] normalIconItems = new int[]{};
    //已选择 图片集合
    private int[] selectIconItems = new int[]{};
    //fragment集合
    private List<Fragment> fragmentList = new ArrayList<>();

    private FragmentManager fragmentManager;

    //Tab点击动画效果
    private Techniques anim = null;
    //ViewPager切换动画
    private boolean smoothScroll = false;
    //图标大小
    private int iconSize;

    //提示红点大小
    private float hintPointSize;
    //提示红点距Tab图标右侧的距离
    private float hintPointLeft;
    //提示红点距图标顶部的距离
    private float hintPointTop;

    private NormalNavigationBar.OnTabClickListener onTabClickListener;
    private OnTabLoadListener onTabLoadListener;

    //消息红点字体大小
    private float msgPointTextSize;
    //消息红点大小
    private float msgPointSize;
    //消息红点99+的长度
    private float msgPointMoreWidth;
    //消息红点99+的高度
    private float msgPointMoreHeight;
    //消息红点99+的半径
    private int msgPointMoreRadius;
    //消息红点颜色
    private int msgPointColor;
    //消息红点距Tab图标右侧的距离   默认为Tab图标的一半
    private float msgPointLeft;
    //消息红点距图标顶部的距离  默认为Tab图标的一半
    private float msgPointTop;
    //Tab文字距Tab图标的距离
    private float tabTextTop;
    //Tab文字大小
    private float tabTextSize;
    //未选中Tab字体颜色
    private int normalTextColor;
    //选中字体颜色
    private int selectTextColor;
    //分割线高度
    private float lineHeight;
    //分割线颜色
    private int lineColor;

    private int navigationBackground;
    private float navigationHeight;

    private ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_INSIDE;

    private boolean canScroll;
    private ViewPagerAdapter adapter;


    private float centerIconSize;
    private float centerLayoutHeight = navigationHeight;

    private float centerLayoutBottomMargin;

    //RULE_CENTER 居中只需调节centerLayoutHeight 默认和navigationHeight相等 此时centerLayoutBottomMargin属性无效
    //RULE_BOTTOM centerLayoutHeight属性无效、自适应、只需调节centerLayoutBottomMargin距底部的距离
    private int centerLayoutRule = RULE_CENTER;

    public static final int RULE_CENTER = 0;
    public static final int RULE_BOTTOM = 1;

    //true  ViewPager在Navigation上面
    //false  ViewPager和Navigation重叠
    private boolean hasPadding;


    //1、普通的Tab 2、中间带按钮（如加号）3、
    private int mode;

    //true 点击加号切换fragment
    //false 点击加号不切换fragment进行其他操作（跳转界面等）
    private boolean centerAsFragment;
    //自定义加号view
    private View customAddView;
    private float centerTextSize;
    //加号文字未选中颜色（默认同其他tab）
    private int centerNormalTextColor;
    //加号文字选中颜色（默认同其他tab）
    private int centerSelectTextColor;
    //加号文字距离顶部加号的距离
    private float centerTextTopMargin;
    //是否和其他tab文字底部对齐
    private boolean centerAlignBottom;
    private View empty_line;

    private int contentType;

    //只是导航没有ViewPager
    private boolean onlyNavigation;
    //记录位置
    private int currentPosition;
    //Tab内容布局方式
    private int tabContentRule;
    //Tab内容距底部距离
    private int tabContentBottomMargin;
    //字体显示为DP还是SP  默认1 为DP 2SP
    private int textSizeType;


    public NormalNavigationBar(Context context) {
        super(context);

        initViews(context, null);
    }

    public NormalNavigationBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initViews(context, attrs);
    }

    private void initViews(Context context, AttributeSet attrs) {

        defaultSetting();

        contentView = (RelativeLayout) View.inflate(context, R.layout.container_layout, null);
        AddContainerLayout = contentView.findViewById(R.id.add_rl);
        empty_line = contentView.findViewById(R.id.empty_line);
        navigationLayout = contentView.findViewById(R.id.navigation_ll);
        lineView = contentView.findViewById(R.id.common_horizontal_line);
        lineView.setTag(-100);
        empty_line.setTag(-100);
        navigationLayout.setTag(-100);


        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.NormalNavigationBar);
        parseStyle(attributes);

        addView(contentView);
    }

    private void parseStyle(TypedArray attributes) {
        if (attributes != null) {
            textSizeType = attributes.getInt(R.styleable.NormalNavigationBar_normal_textSizeType, textSizeType);


            msgPointColor = attributes.getColor(R.styleable.NormalNavigationBar_normal_msgPointColor, msgPointColor);
            navigationHeight = attributes.getDimension(R.styleable.NormalNavigationBar_normal_navigationHeight, navigationHeight);
            navigationBackground = attributes.getColor(R.styleable.NormalNavigationBar_normal_navigationBackground, navigationBackground);

            msgPointMoreWidth = attributes.getDimension(R.styleable.NormalNavigationBar_normal_msgPointMoreWidth, msgPointMoreWidth);
            msgPointMoreHeight = attributes.getDimension(R.styleable.NormalNavigationBar_normal_msgPointMoreHeight, msgPointMoreHeight);
            msgPointMoreRadius = attributes.getInt(R.styleable.NormalNavigationBar_normal_msgPointMoreRadius, msgPointMoreRadius);
            tabTextSize = NavigationUtil.compareTo(getContext(), attributes.getDimension(R.styleable.NormalNavigationBar_normal_tabTextSize, 0), tabTextSize, textSizeType);
            tabTextTop = attributes.getDimension(R.styleable.NormalNavigationBar_normal_tabTextTop, tabTextTop);
            iconSize = (int) attributes.getDimension(R.styleable.NormalNavigationBar_normal_tabIconSize, iconSize);
            hintPointSize = attributes.getDimension(R.styleable.NormalNavigationBar_normal_hintPointSize, hintPointSize);
            msgPointSize = attributes.getDimension(R.styleable.NormalNavigationBar_normal_msgPointSize, msgPointSize);
            hintPointLeft = attributes.getDimension(R.styleable.NormalNavigationBar_normal_hintPointLeft, hintPointLeft);
            msgPointTop = attributes.getDimension(R.styleable.NormalNavigationBar_normal_msgPointTop, -iconSize * 3 / 5);
            hintPointTop = attributes.getDimension(R.styleable.NormalNavigationBar_normal_hintPointTop, hintPointTop);

            msgPointLeft = attributes.getDimension(R.styleable.NormalNavigationBar_normal_msgPointLeft, -iconSize / 2);
            msgPointTextSize = NavigationUtil.compareTo(getContext(), attributes.getDimension(R.styleable.NormalNavigationBar_normal_msgPointTextSize, 0), msgPointTextSize, textSizeType);
            centerIconSize = attributes.getDimension(R.styleable.NormalNavigationBar_normal_centerIconSize, centerIconSize);
            centerLayoutBottomMargin = attributes.getDimension(R.styleable.NormalNavigationBar_normal_centerLayoutBottomMargin, centerLayoutBottomMargin);

            //加号属性
            centerSelectTextColor = attributes.getColor(R.styleable.NormalNavigationBar_normal_centerSelectTextColor, centerSelectTextColor);
            centerNormalTextColor = attributes.getColor(R.styleable.NormalNavigationBar_normal_centerNormalTextColor, centerNormalTextColor);
            centerTextSize = NavigationUtil.compareTo(getContext(), attributes.getDimension(R.styleable.NormalNavigationBar_normal_centerTextSize, 0), centerTextSize, textSizeType);
            centerTextTopMargin = attributes.getDimension(R.styleable.NormalNavigationBar_normal_centerTextTopMargin, centerTextTopMargin);
            centerAlignBottom = attributes.getBoolean(R.styleable.NormalNavigationBar_normal_centerAlignBottom, centerAlignBottom);


            lineHeight = attributes.getDimension(R.styleable.NormalNavigationBar_normal_lineHeight, lineHeight);
            lineColor = attributes.getColor(R.styleable.NormalNavigationBar_normal_lineColor, lineColor);


            centerLayoutHeight = attributes.getDimension(R.styleable.NormalNavigationBar_normal_centerLayoutHeight, navigationHeight + lineHeight);

            normalTextColor = attributes.getColor(R.styleable.NormalNavigationBar_normal_tabNormalColor, normalTextColor);
            selectTextColor = attributes.getColor(R.styleable.NormalNavigationBar_normal_tabSelectColor, selectTextColor);

            int type = attributes.getInt(R.styleable.NormalNavigationBar_normal_scaleType, 0);
            if (type == 0) {
                scaleType = ImageView.ScaleType.CENTER_INSIDE;
            } else if (type == 1) {
                scaleType = ImageView.ScaleType.CENTER_CROP;
            } else if (type == 2) {
                scaleType = ImageView.ScaleType.CENTER;
            } else if (type == 3) {
                scaleType = ImageView.ScaleType.FIT_CENTER;
            } else if (type == 4) {
                scaleType = ImageView.ScaleType.FIT_END;
            } else if (type == 5) {
                scaleType = ImageView.ScaleType.FIT_START;
            } else if (type == 6) {
                scaleType = ImageView.ScaleType.FIT_XY;
            } else if (type == 7) {
                scaleType = ImageView.ScaleType.MATRIX;
            }

            centerLayoutRule = attributes.getInt(R.styleable.NormalNavigationBar_normal_centerLayoutRule, centerLayoutRule);
            hasPadding = attributes.getBoolean(R.styleable.NormalNavigationBar_normal_hasPadding, hasPadding);

            centerAsFragment = attributes.getBoolean(R.styleable.NormalNavigationBar_normal_centerAsFragment, centerAsFragment);
            attributes.recycle();
        }
    }

    public void build() {

        if (centerLayoutHeight < navigationHeight + lineHeight) {
            centerLayoutHeight = navigationHeight + lineHeight;
        }

        if (centerLayoutRule == RULE_CENTER) {
            RelativeLayout.LayoutParams addLayoutParams = (RelativeLayout.LayoutParams) AddContainerLayout.getLayoutParams();
            addLayoutParams.height = (int) centerLayoutHeight;
            AddContainerLayout.setLayoutParams(addLayoutParams);
        } else if (centerLayoutRule == RULE_BOTTOM) {
        }


        navigationLayout.setBackgroundColor(navigationBackground);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) navigationLayout.getLayoutParams();
        params.height = (int) navigationHeight;
        navigationLayout.setLayoutParams(params);


        RelativeLayout.LayoutParams lineParams = (RelativeLayout.LayoutParams) lineView.getLayoutParams();
        lineParams.height = (int) lineHeight;
        lineView.setBackgroundColor(lineColor);
        lineView.setLayoutParams(lineParams);

        //若没有设置中间添加的文字字体大小、颜色、则同其他Tab一样
        if (centerTextSize == 0) {
            centerTextSize = tabTextSize;
        }
        if (centerNormalTextColor == 0) {
            centerNormalTextColor = normalTextColor;
        }
        if (centerSelectTextColor == 0) {
            centerSelectTextColor = selectTextColor;
        }

        if (!checkCanBuild()) {
            return;
        }

        switch (mode) {
            case NavigationMode.MODE_NORMAL:
                buildNavigation();
                break;
            default:
                buildNavigation();
                break;
        }


    }

    /**
     * 重置各个参数
     */
    public NormalNavigationBar defaultSetting() {
        this.titleItems = new String[]{};
        this.normalIconItems = new int[]{};
        this.selectIconItems = new int[]{};
        this.fragmentList = new ArrayList<>();
        if (this.adapter != null) {
            this.adapter.notifyDataSetChanged();
        }

        //Tab点击动画效果
        anim = null;
        //ViewPager切换动画
        smoothScroll = false;
        //图标大小
        iconSize = NavigationUtil.dip2px(getContext(), 20);

        //提示红点大小
        hintPointSize = NavigationUtil.sp2px(getContext(), 6);
        //提示红点距Tab图标右侧的距离
        hintPointLeft = NavigationUtil.dip2px(getContext(), -3);
        //提示红点距图标顶部的距离
        hintPointTop = NavigationUtil.dip2px(getContext(), -3);

        //消息红点字体大小
        msgPointTextSize = 11;
        //消息红点大小
        msgPointSize = NavigationUtil.dip2px(getContext(), 18);
        //消息红点距Tab图标右侧的距离   默认为Tab图标的一半
        msgPointLeft = NavigationUtil.dip2px(getContext(), -10);
        //消息红点距图标顶部的距离  默认为Tab图标的一半
        msgPointTop = NavigationUtil.dip2px(getContext(), -12);
        //Tab文字距Tab图标的距离
        tabTextTop = NavigationUtil.dip2px(getContext(), 2);
        //Tab文字大小
        tabTextSize = 12;
        //未选中Tab字体颜色
        normalTextColor = Color.parseColor("#666666");
        //选中字体颜色
        selectTextColor = Color.parseColor("#333333");
        //分割线高度
        lineHeight = 1;
        //分割线颜色
        lineColor = Color.parseColor("#f7f7f7");

        navigationBackground = Color.parseColor("#ffffff");
        navigationHeight = NavigationUtil.dip2px(getContext(), 60);

        scaleType = ImageView.ScaleType.CENTER_INSIDE;

        canScroll = false;

        centerIconSize = NavigationUtil.dip2px(getContext(), 36);
        centerLayoutHeight = navigationHeight;
        centerLayoutBottomMargin = NavigationUtil.dip2px(getContext(), 10);

        //RULE_CENTER 居中只需调节centerLayoutHeight 默认和navigationHeight相等 此时centerLayoutBottomMargin属性无效
        //RULE_BOTTOM centerLayoutHeight属性无效、自适应、只需调节centerLayoutBottomMargin距底部的距离
        centerLayoutRule = RULE_CENTER;

        //true  ViewPager在Navigation上面
        //false  ViewPager和Navigation重叠
        hasPadding = true;


        //1、普通的Tab 2、中间带按钮（如加号）3、
        mode = NavigationMode.MODE_NORMAL;

        //true 点击加号切换fragment
        //false 点击加号不切换fragment进行其他操作（跳转界面等）
        centerAsFragment = false;

        centerTextSize = 0;
        //加号文字未选中颜色（默认同其他tab）
        centerNormalTextColor = 0;
        //加号文字选中颜色（默认同其他tab）
        centerSelectTextColor = 0;
        //加号文字距离顶部加号的距离
        centerTextTopMargin = NavigationUtil.dip2px(getContext(), 3);
        //是否和其他tab文字底部对齐
        centerAlignBottom = true;

        contentType = TabContentType.TYPE_NORMAL;

        onTabClickListener = null;
        onTabClickListener = null;


        tabContentRule = 0;
        tabContentBottomMargin = 0;
        textSizeType = 1;

        //消息红点99+的长度
        msgPointMoreWidth = NavigationUtil.dip2px(getContext(), 30);
        //消息红点99+的高度
        msgPointMoreHeight = NavigationUtil.dip2px(getContext(), 18);
        //消息红点99+的半径
        msgPointMoreRadius = 10;
        //消息红点颜色
        msgPointColor = Color.parseColor("#ff0000");

        return this;
    }

    /**
     * 更新导航栏UI
     */
    public void updateNavigation(boolean showAnim) {
        if (isCenterAsFragment()) {
            if (isCenterPosition(currentPosition)) {
                selectCenterTabUI();
            } else if (isBeforeCenter(currentPosition)) {
                selectNormalTabUI(currentPosition, showAnim);
            } else {
                selectNormalTabUI(currentPosition - 1, showAnim);
            }
        } else {
            selectNormalTabUI(currentPosition, showAnim);
        }
    }

    @IntDef({
            TextSizeType.TYPE_DP,
            TextSizeType.TYPE_SP,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface TextSizeType {
        //文字单位：1、DP   2、SP
        int TYPE_DP = 1;
        int TYPE_SP = 2;
    }

    @IntDef({
            TabContentType.TYPE_NORMAL,
            TabContentType.TYPE_ONLY_IMAGE,
            TabContentType.TYPE_ONLY_TEXT,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface TabContentType {
        //Tab内容类型：0默认（有选中、未选中两种状态）  1仅图片  2仅文字
        int TYPE_NORMAL = 0;
        int TYPE_ONLY_IMAGE = 1;
        int TYPE_ONLY_TEXT = 2;
    }


    @IntDef({
            NavigationMode.MODE_NORMAL,
            NavigationMode.MODE_ADD,
            NavigationMode.MODE_ADD_VIEW,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface NavigationMode {
        //Tab内容类型：0默认（有选中、未选中两种状态）  1仅图片  2仅文字
        int MODE_NORMAL = 0;
        int MODE_ADD = 1;
        int MODE_ADD_VIEW = 2;
    }

    public void buildNavigation() {

        post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < tabCount; i++) {
                    addTabItemView(i);
                }
                selectNormalTabUI(0, false);
                if (onTabLoadListener != null) {
                    onTabLoadListener.onTabLoadCompleteEvent();
                }
            }
        });


    }

    /**
     * 验证能否构建
     *
     * @return
     */
    private boolean checkCanBuild() {
        if (titleItems.length < 1 && normalIconItems.length < 1) {
            Log.e(getClass().getName(), "titleItems和normalIconItems不能同时为空");
            return false;
        }
        buildCommonNavigation();

        return true;
    }


    /**
     * 构建导航栏前的通用操作
     */
    private void buildCommonNavigation() {
        if (fragmentList == null || fragmentList.size() < 1 || fragmentManager == null) {
            onlyNavigation = true;
        } else {
            onlyNavigation = false;
        }

        if (titleItems == null || titleItems.length < 1) {
            contentType = TabContentType.TYPE_ONLY_IMAGE;
            tabCount = normalIconItems.length;
        } else if (normalIconItems == null || normalIconItems.length < 1) {
            contentType = TabContentType.TYPE_ONLY_TEXT;
            tabCount = titleItems.length;
        } else {
            contentType = TabContentType.TYPE_NORMAL;
            if (titleItems.length > normalIconItems.length) {
                tabCount = titleItems.length;
            } else {
                tabCount = normalIconItems.length;
            }
        }

        if (isAddPage() && tabCount % 2 == 1) {
            Log.e(getClass().getName(), "1.5.0之后、添加中间Tab、则普通Tab数量应为偶数");
            return;
        }

        if (selectIconItems == null || selectIconItems.length < 1) {
            selectIconItems = normalIconItems;
        }


        removeNavigationAllView();

        if (!onlyNavigation) {
            setViewPagerAdapter();
        }

        if (hasPadding && mViewPager != null) {
            mViewPager.setPadding(0, 0, 0, (int) (navigationHeight + lineHeight));
        }
    }

    /**
     * 添加ViewPager
     */
    private void setViewPagerAdapter() {
        if (mViewPager == null) {
            mViewPager = new CustomViewPager(getContext());
            mViewPager.setId(R.id.vp_layout);
            contentView.addView(mViewPager, 0);
        }
        adapter = new ViewPagerAdapter(fragmentManager, fragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(10);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectTab(position, smoothScroll, false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if (canScroll) {
            ((CustomViewPager) getViewPager()).setCanScroll(true);
        } else {
            ((CustomViewPager) getViewPager()).setCanScroll(false);
        }

    }

    /**
     * 是否是前面位置
     *
     * @param position
     * @return
     */
    private boolean isBeforeCenter(int position) {
        if (position < (tabCount / 2))
            return true;
        return false;
    }

    /**
     * 是否是中间位置
     *
     * @param position
     * @return
     */
    private boolean isCenterPosition(int position) {
        if (position == tabCount / 2)
            return true;
        return false;
    }

    /**
     * 生成普通Tab的布局
     */
    private void addTabItemView(final int position) {
        View itemView = View.inflate(getContext(), R.layout.navigation_tab_layout, null);

        LinearLayout ll_tab_content = itemView.findViewById(R.id.ll_tab_content);
        RelativeLayout.LayoutParams llParams = (RelativeLayout.LayoutParams) ll_tab_content.getLayoutParams();

        if (tabContentRule == 0) {
            llParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        } else {
            llParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            llParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            llParams.bottomMargin = tabContentBottomMargin;
        }

        ll_tab_content.setLayoutParams(llParams);


        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        int index = 0;
        if (isCenterAsFragment()) {
            index = position < (tabCount / 2) ? position : position + 1;
        } else {
            index = position;
        }
        final int finalIndex = index;

        if (mode == NavigationMode.MODE_NORMAL) {
            params.width = getWidth() / tabCount;
        } else if (mode == NavigationMode.MODE_ADD) {
            params.width = getWidth() / (tabCount + 1);
        } else if (mode == NavigationMode.MODE_ADD_VIEW) {
            params.width = getWidth() / (tabCount + 1);
        }
        itemView.setTag(R.id.tag_view_position, position);
        itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onTabClickListener != null) {
                    if (currentPosition == position) {
                        onTabClickListener.onTabReSelectEvent(view, currentPosition);
                    }
                    if (!onTabClickListener.onTabSelectEvent(view, position)) {
                        selectTab(finalIndex, smoothScroll);
                    }
                } else {
                    selectTab(finalIndex, smoothScroll);
                }
            }
        });

        itemView.setLayoutParams(params);


        View hintPoint = itemView.findViewById(R.id.red_point);

        //提示红点
        RelativeLayout.LayoutParams hintPointParams = (RelativeLayout.LayoutParams) hintPoint.getLayoutParams();
        hintPointParams.bottomMargin = (int) hintPointTop;
        hintPointParams.width = (int) hintPointSize;
        hintPointParams.height = (int) hintPointSize;
        hintPointParams.leftMargin = (int) hintPointLeft;
        NavigationUtil.setOvalBg(hintPoint, msgPointColor);
        hintPoint.setLayoutParams(hintPointParams);

        //消息红点
        TextView msgPoint = itemView.findViewById(R.id.msg_point_tv);
        msgPoint.setTextSize(textSizeType, msgPointTextSize);
        RelativeLayout.LayoutParams msgPointParams = (RelativeLayout.LayoutParams) msgPoint.getLayoutParams();
        msgPointParams.bottomMargin = (int) NavigationUtil.dip2px(getContext(), -12);
        msgPointParams.leftMargin = (int) msgPointLeft;
        msgPoint.setLayoutParams(msgPointParams);


        hintPointList.add(hintPoint);
        msgPointList.add(msgPoint);

        TextView text = itemView.findViewById(R.id.tab_text_tv);
        ImageView icon = itemView.findViewById(R.id.tab_icon_iv);

        switch (contentType) {
            case TabContentType.TYPE_ONLY_IMAGE:
                text.setVisibility(GONE);

                icon.setScaleType(scaleType);
                LayoutParams iconParams = (LayoutParams) icon.getLayoutParams();
                iconParams.width = (int) iconSize;
                iconParams.height = (int) iconSize;
                icon.setLayoutParams(iconParams);
                imageViewList.add(icon);
                icon.setVisibility(VISIBLE);
                break;
            case TabContentType.TYPE_ONLY_TEXT:
                textViewList.add(text);
                LayoutParams textParams = (LayoutParams) text.getLayoutParams();
                textParams.topMargin = 0;
                text.setLayoutParams(textParams);
                text.setText(titleItems[position]);
                text.setTextSize(textSizeType, tabTextSize);
                text.setVisibility(VISIBLE);

                icon.setVisibility(GONE);
                break;
            default:
                textViewList.add(text);
                LayoutParams textParams2 = (LayoutParams) text.getLayoutParams();
                textParams2.topMargin = (int) tabTextTop;
                text.setLayoutParams(textParams2);
                text.setText(titleItems[position]);
                text.setTextSize(textSizeType, tabTextSize);


                icon.setScaleType(scaleType);
                LayoutParams iconParams2 = (LayoutParams) icon.getLayoutParams();
                iconParams2.width =  iconSize;
                iconParams2.height =  iconSize;
                icon.setLayoutParams(iconParams2);
                imageViewList.add(icon);

                text.setVisibility(VISIBLE);
                icon.setVisibility(VISIBLE);
                break;
        }

        tabList.add(itemView);
        navigationLayout.addView(itemView);
    }

    /**
     * 切换ViewPager页面
     */
    public void selectTab(int position, boolean smoothScroll, boolean selectPager) {
        if (currentPosition == position) {
            return;
        }
        currentPosition = position;
        if (selectPager && mViewPager != null) {
            getViewPager().setCurrentItem(position, smoothScroll);
        }
        updateNavigation(true);
    }

    /**
     * 切换ViewPager页面
     */
    public void selectTab(int position, boolean smoothScroll) {
        selectTab(position, smoothScroll, true);
    }

    /**
     * 是否有中间局部
     *
     * @return
     */
    private boolean isAddPage() {
        if (mode == NavigationMode.MODE_ADD || mode == NavigationMode.MODE_ADD_VIEW)
            return true;
        return false;
    }


    private void removeNavigationAllView() {

        for (int i = 0; i < AddContainerLayout.getChildCount(); i++) {
            if (AddContainerLayout.getChildAt(i).getTag() == null) {
                AddContainerLayout.removeViewAt(i);
            }
        }

        msgPointList.clear();
        hintPointList.clear();
        imageViewList.clear();
        textViewList.clear();
        tabList.clear();

        navigationLayout.removeAllViews();
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }


    /**
     * 选择中间Tab UI变化
     */
    private void selectCenterTabUI() {
        for (int i = 0; i < tabCount; i++) {
            switch (contentType) {
                case TabContentType.TYPE_NORMAL:
                    imageViewList.get(i).setImageResource(normalIconItems[i]);
                    textViewList.get(i).setTextColor(normalTextColor);
                    textViewList.get(i).setText(titleItems[i]);
                case TabContentType.TYPE_ONLY_IMAGE:
                    imageViewList.get(i).setImageResource(normalIconItems[i]);
                    break;
                case TabContentType.TYPE_ONLY_TEXT:
                    textViewList.get(i).setTextColor(normalTextColor);
                    textViewList.get(i).setText(titleItems[i]);
                    break;
            }
        }
    }

    /**
     * 选择普通tab UI变化
     *
     * @param position
     */
    private void selectNormalTabUI(int position, boolean showAnim) {
        for (int i = 0; i < tabCount; i++) {
            if (i == position) {
                if (anim != null && showAnim) {
                    YoYo.with(anim).duration(300).playOn(tabList.get(i));
                }
                switch (contentType) {
                    case TabContentType.TYPE_NORMAL:
                        imageViewList.get(i).setImageResource(selectIconItems[i]);
                        textViewList.get(i).setTextColor(selectTextColor);
                        textViewList.get(i).setText(titleItems[i]);
                        break;
                    case TabContentType.TYPE_ONLY_IMAGE:
                        imageViewList.get(i).setImageResource(selectIconItems[i]);
                        break;
                    case TabContentType.TYPE_ONLY_TEXT:
                        textViewList.get(i).setTextColor(selectTextColor);
                        textViewList.get(i).setText(titleItems[i]);
                        break;
                }

            } else {
                switch (contentType) {
                    case TabContentType.TYPE_NORMAL:
                        imageViewList.get(i).setImageResource(normalIconItems[i]);
                        textViewList.get(i).setTextColor(normalTextColor);
                        textViewList.get(i).setText(titleItems[i]);
                    case TabContentType.TYPE_ONLY_IMAGE:
                        imageViewList.get(i).setImageResource(normalIconItems[i]);
                        break;
                    case TabContentType.TYPE_ONLY_TEXT:
                        textViewList.get(i).setTextColor(normalTextColor);
                        textViewList.get(i).setText(titleItems[i]);
                        break;
                }
            }
        }

    }


    public interface OnTabClickListener {
        boolean onTabSelectEvent(View view, int position);

        /**
         * 重复点击
         */
        boolean onTabReSelectEvent(View view, int position);
    }

    public interface OnTabLoadListener {
        /**
         * Tab加载完毕
         */
        void onTabLoadCompleteEvent();
    }


    public NormalNavigationBar titleItems(String[] titleItems) {
        this.titleItems = titleItems;
        return this;
    }

    public NormalNavigationBar normalIconItems(int[] normalIconItems) {
        this.normalIconItems = normalIconItems;
        return this;
    }

    public NormalNavigationBar selectIconItems(int[] selectIconItems) {
        this.selectIconItems = selectIconItems;
        return this;
    }

    public NormalNavigationBar fragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
        return this;
    }

    public NormalNavigationBar fragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        return this;
    }

    public NormalNavigationBar canScroll(boolean canScroll) {
        this.canScroll = canScroll;
        return this;
    }


    public boolean isCenterAsFragment() {
        return centerAsFragment && isAddPage();
    }
}
