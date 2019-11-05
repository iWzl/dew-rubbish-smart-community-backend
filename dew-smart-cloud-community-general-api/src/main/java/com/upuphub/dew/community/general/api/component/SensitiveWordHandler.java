package com.upuphub.dew.community.general.api.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 敏感词的拦截处理
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/7/25 22:01
 */

@Slf4j
@Component
public class SensitiveWordHandler {
    /**
     * 敏感词匹配规则
     * 最小匹配规则，如：敏感词库["中国","中国人"]，语句："我是中国人"，匹配结果：我是[中国]人
     * 最大匹配规则，如：敏感词库["中国","中国人"]，语句："我是中国人"，匹配结果：我是[中国人]
     * 默认掩码
     */
    public static final int MIN_MATCH_TYPE = 1;
    public static final int MAX_MATCH_TYPE = 2;
    private static final char MASK_CHAR = '*';

    /**
     * 敏感词集合
     */
    private static HashMap sensitiveWordMap;

    public SensitiveWordHandler(@Value("${dew.sensitive.dict}") String path) {
        init(initializationWork(path));
    }

    /**
     * 初始化敏感词库，构建DFA算法模型
     *
     * @param sensitiveWordSet 敏感词库
     */
    private synchronized void init(Set<String> sensitiveWordSet) {
        initSensitiveWordMap(sensitiveWordSet);
        log.info("Init Sensitive Words {} Successes",sensitiveWordMap.size());
    }

    /**
     * 初始化敏感词库，构建DFA算法模型
     *
     * @param sensitiveWordSet 敏感词库
     */
    private static void initSensitiveWordMap(Set<String> sensitiveWordSet) {
        //初始化敏感词容器，减少扩容操作
        sensitiveWordMap = new HashMap(sensitiveWordSet.size());
        String key;
        Map nowMap;
        Map<String, String> newWorMap;
        //迭代sensitiveWordSet
        for (String s : sensitiveWordSet) {
            //关键字
            key = s.toLowerCase();
            nowMap = sensitiveWordMap;
            for (int i = 0; i < key.length(); i++) {
                //转换成char型
                char keyChar = key.charAt(i);
                //库中获取关键字
                Object wordMap = nowMap.get(keyChar);
                //如果存在该key，直接赋值，用于下一个循环获取
                if (wordMap != null) {
                    nowMap = (Map) wordMap;
                } else {
                    //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    newWorMap = new HashMap<>();
                    //不是最后一个
                    newWorMap.put("isEnd", "0");
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }

                if (i == key.length() - 1) {
                    //最后一个
                    nowMap.put("isEnd", "1");
                }
            }
        }
    }

    /**
     * 判断文字是否包含敏感字符
     *
     * @param txt       文字
     * @param matchType 匹配规则 1：最小匹配规则，2：最大匹配规则
     * @return 若包含返回true，否则返回false
     */
    public boolean contains(String txt, int matchType) {
        boolean flag = false;
        for (int i = 0; i < txt.length(); i++) {
            //判断是否包含敏感字符
            int matchFlag = checkSensitiveWord(txt, i, matchType);
            //大于0存在，返回true
            if (matchFlag > 0) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 判断文字是否包含敏感字符
     *
     * @param txt 文字
     * @return 若包含返回true，否则返回false
     */
    public boolean contains(String txt) {
        return contains(txt, MAX_MATCH_TYPE);
    }

    /**
     * 获取文字中的敏感词
     *
     * @param txt       文字
     * @param matchType 匹配规则 1：最小匹配规则，2：最大匹配规则
     * @return 文字中的敏感词
     */
    public Set<String> getSensitiveWord(String txt, int matchType) {
        Set<String> sensitiveWordList = new HashSet<>();

        for (int i = 0; i < txt.length(); i++) {
            //判断是否包含敏感字符
            int length = checkSensitiveWord(txt, i, matchType);
            //存在,加入list中
            if (length > 0) {
                sensitiveWordList.add(txt.substring(i, i + length));
                //减1的原因，是因为for会自增
                i = i + length - 1;
            }
        }
        return sensitiveWordList;
    }

    /**
     * 获取文字中的敏感词
     *
     * @param txt 文字
     * @return 文字中的敏感词
     */
    public Set<String> getSensitiveWord(String txt) {
        return getSensitiveWord(txt, MAX_MATCH_TYPE);
    }

    /**
     * 替换敏感字字符
     *
     * @param txt         文本
     * @param replaceChar 替换的字符，匹配的敏感词以字符逐个替换，如 语句：我爱中国人 敏感词：中国人，替换字符：*， 替换结果：我爱***
     * @param matchType   敏感词匹配规则
     * @return 替换后的参数文本
     */
    public String replaceSensitiveWord(String txt, char replaceChar, int matchType) {
        String resultTxt = txt;
        //获取所有的敏感词
        Set<String> set = getSensitiveWord(txt, matchType);
        Iterator<String> iterator = set.iterator();
        String word;
        String replaceString;
        while (iterator.hasNext()) {
            word = iterator.next();
            replaceString = getReplaceChars(replaceChar, word.length());
            resultTxt = resultTxt.replaceAll(word, replaceString);
        }

        return resultTxt;
    }

    /**
     * 替换敏感字字符
     *
     * @param txt         文本
     * @param replaceChar 替换的字符，匹配的敏感词以字符逐个替换，如 语句：我爱中国人 敏感词：中国人，替换字符：*， 替换结果：我爱***
     * @return 替换后的参数文本
     */
    public String replaceSensitiveWord(String txt, char replaceChar) {
        return replaceSensitiveWord(txt, replaceChar, MAX_MATCH_TYPE);
    }

    /**
     * 替换敏感字字符
     *
     * @param txt        文本
     * @param replaceStr 替换的字符串，匹配的敏感词以字符逐个替换，如 语句：我爱中国人 敏感词：中国人，替换字符串：[屏蔽]，替换结果：我爱[屏蔽]
     * @param matchType  敏感词匹配规则
     * @return 替换后的参数文本
     */
    public String replaceSensitiveWord(String txt, String replaceStr, int matchType) {
        String resultTxt = txt;
        //获取所有的敏感词
        Set<String> set = getSensitiveWord(txt, matchType);
        Iterator<String> iterator = set.iterator();
        String word;
        while (iterator.hasNext()) {
            word = iterator.next();
            resultTxt = resultTxt.replaceAll(word, replaceStr);
        }

        return resultTxt;
    }

    /**
     * 替换敏感字字符
     *
     * @param txt       文本
     * @param matchType 敏感词匹配规则
     */
    public String replaceSensitiveWord(String txt, int matchType) {
        String resultTxt = txt;
        //获取所有的敏感词
        Set<String> set = getSensitiveWord(txt, matchType);
        Iterator<String> iterator = set.iterator();
        String word;
        while (iterator.hasNext()) {
            word = iterator.next();
            resultTxt = resultTxt.replaceAll(word, getReplaceChars(MASK_CHAR, word.length()));
        }

        return resultTxt;
    }

    /**
     * 替换敏感字字符
     *
     * @param txt        文本
     * @param replaceStr 替换的字符串，匹配的敏感词以字符逐个替换，如 语句：我爱中国人 敏感词：中国人，替换字符串：[屏蔽]，替换结果：我爱[屏蔽]
     * @return 替换后的参数文本
     */
    public String replaceSensitiveWord(String txt, String replaceStr) {
        return replaceSensitiveWord(txt, replaceStr, MAX_MATCH_TYPE);
    }

    /**
     * 获取替换字符串
     *
     * @param replaceChar 替换使用的关键词
     * @param length      替换的长度
     * @return 替换后的字符串
     */
    private String getReplaceChars(char replaceChar, int length) {
        StringBuilder resultReplace = new StringBuilder(String.valueOf(replaceChar));
        for (int i = 1; i < length; i++) {
            resultReplace.append(replaceChar);
        }
        return resultReplace.toString();
    }

    /**
     * 检查文字中是否包含敏感字符，检查规则如下：
     *
     * @param txt        需要检查的文本
     * @param beginIndex 需要检查的文本的起点位置
     * @param matchType  敏感词的匹配规则
     * @return 如果存在，则返回敏感词字符的长度，不存在返回0
     */
    private int checkSensitiveWord(String txt, int beginIndex, int matchType) {
        //敏感词结束标识位：用于敏感词只有1位的情况
        boolean flag = false;
        //匹配标识数默认为0
        int matchFlag = 0;
        char word;
        Map nowMap = sensitiveWordMap;
        for (int i = beginIndex; i < txt.length(); i++) {
            word = txt.charAt(i);
            //获取指定key
            nowMap = (Map) nowMap.get(word);
            //存在，则判断是否为最后一个
            if (nowMap != null) {
                //找到相应key，匹配标识+1
                matchFlag++;
                //如果为最后一个匹配规则,结束循环，返回匹配标识数
                if ("1".equals(nowMap.get("isEnd"))) {
                    //结束标志位为true
                    flag = true;
                    //最小规则，直接返回,最大规则还需继续查找
                    if (MIN_MATCH_TYPE == matchType) {
                        break;
                    }
                }
            } else {
                //不存在，直接返回
                break;
            }
        }
        if (matchFlag < 2 || !flag) {
            //长度必须大于等于1，为词
            matchFlag = 0;
        }
        return matchFlag;
    }


    /**
     * 初始化敏感词库
     */
    private Set<String> initializationWork(String path) {
        Set<String> sensitiveKeys = new HashSet<>();
        try (InputStreamReader read = new InputStreamReader(
                new DefaultResourceLoader()
                        .getResource(path)
                        .getInputStream())) {
            BufferedReader bufferedReader = null;
            bufferedReader = new BufferedReader(read);
            for (String txt = null; (txt = bufferedReader.readLine()) != null; ) {
                sensitiveKeys.add(txt);
            }
            return sensitiveKeys;
        } catch (IOException e) {
            return Collections.emptySet();
        }
    }
}
