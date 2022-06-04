package cn;

//每个 有效电子邮件地址 都由一个 本地名 和一个 域名 组成，以 '@' 符号分隔。除小写字母之外，电子邮件地址还可以含有一个或多个 '.' 或 '+' 。
// 
//
// 
// 例如，在 alice@leetcode.com中， alice 是 本地名 ，而 leetcode.com 是 域名 。 
// 
//
// 如果在电子邮件地址的 本地名 部分中的某些字符之间添加句点（'.'），则发往那里的邮件将会转发到本地名中没有点的同一地址。请注意，此规则 不适用于域名 。
// 
//
// 
// 例如，"alice.z@leetcode.com” 和 “alicez@leetcode.com” 会转发到同一电子邮件地址。 
// 
//
// 如果在 本地名 中添加加号（'+'），则会忽略第一个加号后面的所有内容。这允许过滤某些电子邮件。同样，此规则 不适用于域名 。 
//
// 
// 例如 m.y+name@email.com 将转发到 my@email.com。 
// 
//
// 可以同时使用这两个规则。 
//
// 给你一个字符串数组 emails，我们会向每个 emails[i] 发送一封电子邮件。返回实际收到邮件的不同地址数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：emails = ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.co
//m","testemail+david@lee.tcode.com"]
//输出：2
//解释：实际收到邮件的是 "testemail@leetcode.com" 和 "testemail@lee.tcode.com"。
// 
//
// 示例 2： 
//
// 
//输入：emails = ["a@leetcode.com","b@leetcode.com","c@leetcode.com"]
//输出：3
// 
//
// 
//提示： 
//
// 
// 1 <= emails.length <= 100 
// 1 <= emails[i].length <= 100 
// emails[i] 由小写英文字母、'+'、'.' 和 '@' 组成 
// 每个 emails[i] 都包含有且仅有一个 '@' 字符 
// 所有本地名和域名都不为空 
// 本地名不会以 '+' 字符作为开头 
// 
// Related Topics 数组 哈希表 字符串 
// 👍 188 👎 0


import java.util.HashSet;
import java.util.Set;

/**
 * 执行耗时分别是10ms/9ms/7ms
 *
 * @author liuchenguang002
 */
public class UniqueEmailAddresses_929 {
    public static void main(String[] args) {
        String[] emails = {"test.email+alex@leetcode.com", "test.email@leetcode.com"};
        Solution solution = new Solution();
        solution.charArray(emails);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numUniqueEmails(String[] emails) {
            Set<String> emailSet = new HashSet<>(emails.length);
            for (String cur : emails) {
                StringBuilder builder = new StringBuilder();
                boolean meet = false;
                for (int i = 0; i < cur.length(); i++) {
                    if (cur.charAt(i) == '.' && !meet) {
                        continue;
                    }
                    if (cur.charAt(i) == '+' && !meet) {
                        while (cur.charAt(i) != '@') {
                            i++;
                        }
                        builder.append(cur.substring(i));
                        break;
                    }
                    if (cur.charAt(i) == '@') {
                        meet = true;
                    }
                    builder.append(cur.charAt(i));
                }
                emailSet.add(builder.toString());
            }
            return emailSet.size();
        }

        public int regexp(String[] emails) {
            Set<String> emailSet = new HashSet<>(emails.length);
            for (String cur : emails) {
                int i = cur.indexOf('@');
                String local = cur.substring(0, i).split("\\+")[0].replace(".", "");
                emailSet.add(local + cur.substring(i));
            }
            return emailSet.size();
        }


        public int charArray(String[] emails) {
            Set<String> emailSet = new HashSet<>(emails.length);
            for (String cur : emails) {
                char[] chars = cur.toCharArray();
                boolean meet = false;
                int pos = 0;
                for (int i = 0; i < cur.length(); i++) {
                    if (cur.charAt(i) == '.' && !meet) {
                        continue;
                    }
                    if (cur.charAt(i) == '+' && !meet) {
                        while (cur.charAt(i) != '@') {
                            i++;
                        }
                        while (i < cur.length()) {
                            chars[pos++] = chars[i++];
                        }
                        break;
                    }
                    if (cur.charAt(i) == '@') {
                        meet = true;
                    }
                    chars[pos++] = chars[i];
                }
                emailSet.add(new String(chars, 0, pos));
            }
            return emailSet.size();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}