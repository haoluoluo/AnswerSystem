package com.luoluohao.answer_system.x.asUtils;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import com.luoluohao.answer_system.util.StringUtil;
import com.luoluohao.answer_system.util.ViewJSON;

/**
 * @author : S_ven
 * @create : 2018/9/22 下午5:55
 * @describe: ProjectUtil
 */
public class ProjectUtil {

    private static final class ProjectUtilHolder {
        private static final ProjectUtil INSTANCE = new ProjectUtil();
    }

    public static ProjectUtil getInstance() {
        return ProjectUtilHolder.INSTANCE;
    }

    private SimpleDateFormat sdf;

    public Date formateDate(String time) {
        if (sdf == null) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        }

        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    private Map<String, String> getMapProperty(String fileName) {
        ResourceBundle resource = ResourceBundle.getBundle(fileName, new Locale("zh", "CN"));
        Map<String, String> map = new HashMap<>();

        Enumeration en = resource.getKeys();
        while (en.hasMoreElements()) {
            String key = (String) en.nextElement();
            String value = resource.getString(key);
            map.put(key, value);
        }
        return map;
    }

    public String smsReplace(String source, HashMap<String, Object> replacements) {
        return StringUtil.Replace(source, replacements, "{", "}");
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    // 返回结果处理
    public interface ResultListener {

        void onSuccess(JSONObject object);

        void refreshToken();
    }

//    /**
//     * sina wechat操作
//     *
//     * @param json 范围结果
//     * @return 操作结果
//     */
//    public static ViewJSON thridResult(
//            int type,
//            String json,
//            String hint,
//            String memo,
//            String info,
//            OpenModel om,
//            ResultListener listener) {
//        // 构建返回结果
//        JSONObject object = null;
//        String result_code = "400";
//        if (!isEmpty(json)) {
//            // 返回json不为空
//            object = JSONObject.parseObject(json);
//            Integer code;
//            String typeName = "";
//            switch (type) {
//                case 3:
//                    // 微博
//                    result_code = "9998";
//                    typeName = "微博";
//                    code = object.getInteger("error_code");
//                    info = object.getString("error");
//                    break;
//                case 5:
//                    //企鹅号
//                    result_code = "9997";
//                    typeName = "企鹅号";
//                    code = object.getInteger("code");
//                    info = object.getString("msg");
//                    break;
//                default:
//                    // 微信
//                    code = object.getInteger("errcode");
//            }
//
//            if (code == null || code == 0) {
//                // 没有errcode或者code=0，在操作中视为成功
//                result_code = "200";
//                if (listener != null) {
//                    listener.onSuccess(object);
//                }
//                return new ViewJSON<>(result_code, hint + "成功", memo, info, object);
//            } else {
//                switch (type) {
//                    case 3:
//                        //微博
//                        if (code == 21314
//                                || code == 21322
//                                || code == 21315
//                                || code == 21316
//                                || code == 21317
//                                || code == 21327) {
//                            // 微博需要前端配合刷新token
//                            result_code = "9999";
//                            info = typeName + "需要刷新token";
//                        }
//                        break;
//                    case 5:
//                        if (code == 40015) {
//                            // 企鹅号需要前端配合刷新token
//                            result_code = "9999";
//                            info = typeName + "需要刷新token";
//                        }
//                        //企鹅号
//                        break;
//                    default:
//                        if (code == 40014 || code == 42001) {
//                            // 微信不需要前端配合
//                            if (listener != null) {
//                                listener.refreshToken();
//                            }
//                        }
//                        break;
//                }
//            }
//        }
//        if (om != null) {
//            return new ViewJSON<>(result_code, hint + "失败", memo, info, om);
//        }
//        return new ViewJSON<>(result_code, hint + "失败", memo, info, object);
//    }
//
//
//    public static ViewJSON<Object> thridResult(
//            int type, String json, String hint, String memo, String info, OpenModel om) {
//        return thridResult(type, json, hint, memo, info, om, null);
//    }
//
//    public static ViewJSON<Object> thridResult(
//            int type, String json, String hint, String memo, String info) {
//        return thridResult(type, json, hint, memo, info, null);
//    }
//
//    public static ViewJSON<Object> thridResult(
//            int type, String json, String hint, ResultListener listener) {
//        return thridResult(type, json, hint, "", "", null, listener);
//    }
//
//    public static ViewJSON<Object> thridResult(
//            int type, String json, String hint, OpenModel om, ResultListener listener) {
//        return thridResult(type, json, hint, "", "", om, listener);
//    }
//
//    public static ViewJSON<Object> thridResult(int type, String json, String hint, String memo) {
//        return thridResult(type, json, hint, memo, "");
//    }
//
//    public static ViewJSON<Object> thridResult(int type, String json, String hint) {
//        return thridResult(type, json, hint, "");
//    }
//
//    public static ViewJSON<Object> thridResult(int type, String json) {
//        return thridResult(type, json, "");
//    }

    /**
     * 生成六位验证码
     *
     * @return 六位验证码
     */
    public String getSix() {
        Random rad = new Random();

        String result = rad.nextInt(1000000) + "";

        if (result.length() != 6) {
            return getSix();
        }
        return result;
    }

//    /**
//     * 合并属性
//     *
//     * @param sourceBean 被提取的对象bean
//     * @param targetBean 用于合并的对象bean
//     * @return 合并结果
//     */
//    public SearchStock combineSydwCore(SearchStock sourceBean, SearchStock targetBean) {
//        Class sourceBeanClass = sourceBean.getClass();
//        Field[] sourceFields = sourceBeanClass.getDeclaredFields();
//        Field[] targetFields = sourceBeanClass.getDeclaredFields();
//        for (int i = 0; i < sourceFields.length; i++) {
//            Field sourceField = sourceFields[i];
//            Field targetField = targetFields[i];
//            sourceField.setAccessible(true);
//            targetField.setAccessible(true);
//            try {
//                if (!(sourceField.get(sourceBean) == null)) {
//                    targetField.set(targetBean, sourceField.get(sourceBean));
//                }
//            } catch (IllegalArgumentException | IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//        return targetBean;
//    }
}
