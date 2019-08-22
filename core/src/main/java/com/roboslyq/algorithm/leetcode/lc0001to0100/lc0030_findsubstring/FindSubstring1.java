package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0030_findsubstring;

import java.util.*;

/*
 * 1、将数组中的所有字符组合放入Map中，作为key
 * 2、遍列源串s,窗口滑动右移，判断是否在Map中是否存在当前Key。
 */
public class FindSubstring1 {


    public static void main(String[] args) {
        FindSubstring1 findSubstring = new FindSubstring1();
//        List<String> words = new LinkedList<>();
//        words.add("a");
//        words.add("b");
//        words.add("c");
//        words.add("d");
//        for (Map.Entry entry : findSubstring.res.entrySet()) {
//            System.out.println(entry.getKey());
//        }
         //s = "barfoothefoobarman",
        //  words = ["foo","bar"]

//        List<Integer> list = findSubstring.findSubstring("barfoothefoobarman",new String[]{"foo","bar"});
//        List<Integer> list = findSubstring.findSubstring("",new String[]{""});
        List<Integer> list = findSubstring.findSubstring("pjzkrkevzztxductzzxmxsvwjkxpvukmfjywwetvfnujhweiybwvvsrfequzkhossmootkmyxgjgfordrpapjuunmqnxxdrqrfgkrsjqbszgiqlcfnrpjlcwdrvbumtotzylshdvccdmsqoadfrpsvnwpizlwszrtyclhgilklydbmfhuywotjmktnwrfvizvnmfvvqfiokkdprznnnjycttprkxpuykhmpchiksyucbmtabiqkisgbhxngmhezrrqvayfsxauampdpxtafniiwfvdufhtwajrbkxtjzqjnfocdhekumttuqwovfjrgulhekcpjszyynadxhnttgmnxkduqmmyhzfnjhducesctufqbumxbamalqudeibljgbspeotkgvddcwgxidaiqcvgwykhbysjzlzfbupkqunuqtraxrlptivshhbihtsigtpipguhbhctcvubnhqipncyxfjebdnjyetnlnvmuxhzsdahkrscewabejifmxombiamxvauuitoltyymsarqcuuoezcbqpdaprxmsrickwpgwpsoplhugbikbkotzrtqkscekkgwjycfnvwfgdzogjzjvpcvixnsqsxacfwndzvrwrycwxrcismdhqapoojegggkocyrdtkzmiekhxoppctytvphjynrhtcvxcobxbcjjivtfjiwmduhzjokkbctweqtigwfhzorjlkpuuliaipbtfldinyetoybvugevwvhhhweejogrghllsouipabfafcxnhukcbtmxzshoyyufjhzadhrelweszbfgwpkzlwxkogyogutscvuhcllphshivnoteztpxsaoaacgxyaztuixhunrowzljqfqrahosheukhahhbiaxqzfmmwcjxountkevsvpbzjnilwpoermxrtlfroqoclexxisrdhvfsindffslyekrzwzqkpeocilatftymodgztjgybtyheqgcpwogdcjlnlesefgvimwbxcbzvaibspdjnrpqtyeilkcspknyylbwndvkffmzuriilxagyerjptbgeqgebiaqnvdubrtxibhvakcyotkfonmseszhczapxdlauexehhaireihxsplgdgmxfvaevrbadbwjbdrkfbbjjkgcztkcbwagtcnrtqryuqixtzhaakjlurnumzyovawrcjiwabuwretmdamfkxrgqgcdgbrdbnugzecbgyxxdqmisaqcyjkqrntxqmdrczxbebemcblftxplafnyoxqimkhcykwamvdsxjezkpgdpvopddptdfbprjustquhlazkjfluxrzopqdstulybnqvyknrchbphcarknnhhovweaqawdyxsqsqahkepluypwrzjegqtdoxfgzdkydeoxvrfhxusrujnmjzqrrlxglcmkiykldbiasnhrjbjekystzilrwkzhontwmehrfsrzfaqrbbxncphbzuuxeteshyrveamjsfiaharkcqxefghgceeixkdgkuboupxnwhnfigpkwnqdvzlydpidcljmflbccarbiegsmweklwngvygbqpescpeichmfidgsjmkvkofvkuehsmkkbocgejoiqcnafvuokelwuqsgkyoekaroptuvekfvmtxtqshcwsztkrzwrpabqrrhnlerxjojemcxel",new String[]{"dhvf","sind","ffsl","yekr","zwzq","kpeo","cila","tfty","modg","ztjg","ybty","heqg","cpwo","gdcj","lnle","sefg","vimw","bxcb"});
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
    public List<Integer> findSubstring(String s, String[] words) {
        //结果保存域
        List<Integer> res = new ArrayList<>();
        //判断特殊情况
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
        //将字符串保存到数组当中，并且保存重复的个数
        HashMap<String, Integer> map = new HashMap<>();
        int one_word = words[0].length();
        //匹配次数
        int word_num = words.length;
//        int all_len = one_word * word_num; //总长度
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        //单个单词长度循环
        for (int i = 0; i < one_word; i++) {
            //双向指针法
            int left = i, right = i, count = 0;
            //临时Map
            HashMap<String, Integer> tmp_map = new HashMap<>();
            //递归处理原串
            while (right + one_word <= s.length()) {
                //获取原串第一个字符串（长度为one_word）
                String w = s.substring(right, right + one_word);
                right += one_word;//下一个串的起始位置
                if (!map.containsKey(w)) {//如果临时Map不包含这个串，则表示当前字符串不在结果集中，重置相关计数器
                    count = 0;
                    left = right;
                    tmp_map.clear();
                } else {
                    //如果在map串中，则保存到临时Map中
                    tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
                    count++;//匹配值+1
                    while (tmp_map.getOrDefault(w, 0) > map.getOrDefault(w, 0)) {
                        String t_w = s.substring(left, left + one_word);
                        count--;
                        tmp_map.put(t_w, tmp_map.getOrDefault(t_w, 0) - 1);
                        left += one_word;
                    }
                    if (count == word_num) res.add(left);
                }
            }
        }
        return res;

    }
}
