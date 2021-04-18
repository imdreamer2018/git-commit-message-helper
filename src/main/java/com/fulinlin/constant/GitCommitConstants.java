package com.fulinlin.constant;

/**
 * @program: git-commit-message-helper
 * @author: fulin
 * @create: 2019-12-08 11:37
 **/
public class GitCommitConstants {
    public static final String DEFAULT_TEMPLATE = "#if($author)[${author}]#end #if($card)#${card}#end #if($type)${type}#end: #if($subject)${subject}#end\n" +
            "#if($body)${newline}${newline}${body}#end\n";
}
