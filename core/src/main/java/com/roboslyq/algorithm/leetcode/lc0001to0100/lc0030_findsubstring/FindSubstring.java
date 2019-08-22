package com.roboslyq.algorithm.leetcode.lc0001to0100.lc0030_findsubstring;

import java.util.*;
/*
 * 1、将数组中的所有字符组合放入Map中，作为key
 * 2、遍列源串s,窗口滑动右移，判断是否在Map中是否存在当前Key。
 */
public class FindSubstring {


    public static void main(String[] args) {
        FindSubstring findSubstring = new FindSubstring();
        List<String> words = new LinkedList<>();
        words.add("a");
        words.add("b");
        words.add("c");
        words.add("d");
        findSubstring.initWords(words,"");
        for (Map.Entry entry : findSubstring.res.entrySet()) {
            System.out.println(entry.getKey());
        }
         //s = "barfoothefoobarman",
        //  words = ["foo","bar"]

//        List<Integer> list = findSubstring.findSubstring("barfoothefoobarman",new String[]{"foo","bar"});
//        List<Integer> list = findSubstring.findSubstring("",new String[]{""});
//        List<Integer> list = findSubstring.findSubstring("pjzkrkevzztxductzzxmxsvwjkxpvukmfjywwetvfnujhweiybwvvsrfequzkhossmootkmyxgjgfordrpapjuunmqnxxdrqrfgkrsjqbszgiqlcfnrpjlcwdrvbumtotzylshdvccdmsqoadfrpsvnwpizlwszrtyclhgilklydbmfhuywotjmktnwrfvizvnmfvvqfiokkdprznnnjycttprkxpuykhmpchiksyucbmtabiqkisgbhxngmhezrrqvayfsxauampdpxtafniiwfvdufhtwajrbkxtjzqjnfocdhekumttuqwovfjrgulhekcpjszyynadxhnttgmnxkduqmmyhzfnjhducesctufqbumxbamalqudeibljgbspeotkgvddcwgxidaiqcvgwykhbysjzlzfbupkqunuqtraxrlptivshhbihtsigtpipguhbhctcvubnhqipncyxfjebdnjyetnlnvmuxhzsdahkrscewabejifmxombiamxvauuitoltyymsarqcuuoezcbqpdaprxmsrickwpgwpsoplhugbikbkotzrtqkscekkgwjycfnvwfgdzogjzjvpcvixnsqsxacfwndzvrwrycwxrcismdhqapoojegggkocyrdtkzmiekhxoppctytvphjynrhtcvxcobxbcjjivtfjiwmduhzjokkbctweqtigwfhzorjlkpuuliaipbtfldinyetoybvugevwvhhhweejogrghllsouipabfafcxnhukcbtmxzshoyyufjhzadhrelweszbfgwpkzlwxkogyogutscvuhcllphshivnoteztpxsaoaacgxyaztuixhunrowzljqfqrahosheukhahhbiaxqzfmmwcjxountkevsvpbzjnilwpoermxrtlfroqoclexxisrdhvfsindffslyekrzwzqkpeocilatftymodgztjgybtyheqgcpwogdcjlnlesefgvimwbxcbzvaibspdjnrpqtyeilkcspknyylbwndvkffmzuriilxagyerjptbgeqgebiaqnvdubrtxibhvakcyotkfonmseszhczapxdlauexehhaireihxsplgdgmxfvaevrbadbwjbdrkfbbjjkgcztkcbwagtcnrtqryuqixtzhaakjlurnumzyovawrcjiwabuwretmdamfkxrgqgcdgbrdbnugzecbgyxxdqmisaqcyjkqrntxqmdrczxbebemcblftxplafnyoxqimkhcykwamvdsxjezkpgdpvopddptdfbprjustquhlazkjfluxrzopqdstulybnqvyknrchbphcarknnhhovweaqawdyxsqsqahkepluypwrzjegqtdoxfgzdkydeoxvrfhxusrujnmjzqrrlxglcmkiykldbiasnhrjbjekystzilrwkzhontwmehrfsrzfaqrbbxncphbzuuxeteshyrveamjsfiaharkcqxefghgceeixkdgkuboupxnwhnfigpkwnqdvzlydpidcljmflbccarbiegsmweklwngvygbqpescpeichmfidgsjmkvkofvkuehsmkkbocgejoiqcnafvuokelwuqsgkyoekaroptuvekfvmtxtqshcwsztkrzwrpabqrrhnlerxjojemcxel",new String[]{"dhvf","sind","ffsl","yekr","zwzq","kpeo","cila","tfty","modg","ztjg","ybty","heqg","cpwo","gdcj","lnle","sefg","vimw","bxcb"});
//        list.stream().forEach(System.out::println);
    }
    Map<String,Object> res = new HashMap<>();
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> resInteger = new ArrayList<>();
        if(words == null || words.length == 0){
            return resInteger;
        }
        int wordsLength = words[0].length() * words.length;
        if(s == null || s.length() == 0 || s.length() < wordsLength){
            return resInteger;
        }
        initWords(Arrays.asList(words),"");
        for(int i = 0; i<s.length()-wordsLength+1; i++){
            String resTmp = s.substring(i,i+wordsLength);
            if(res.containsKey(resTmp)){
                resInteger.add(i);
            }
        }
        return resInteger;
    }
    public void initWords(List<String> words,String prefix){
        if(words.size() == 1){
            res.put(prefix + words.get(0),null);
            return;
        }
        for(int i = 0; i<words.size();i++){
            List<String> tmp = new LinkedList<String>(words);
            initWords(tmp, prefix + tmp.remove(i));
        }
    }
}
