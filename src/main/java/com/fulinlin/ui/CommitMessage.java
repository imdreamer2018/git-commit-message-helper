package com.fulinlin.ui;

import com.fulinlin.model.CommitTemplate;
import com.fulinlin.model.TypeAlias;
import com.fulinlin.storage.GitCommitMessageHelperSettings;
import com.fulinlin.utils.VelocityUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author fulin
 */
public class CommitMessage {
    private static final int MAX_LINE_LENGTH = 72; // https://stackoverflow.com/a/2120040/5138796
    private final String content;

    public CommitMessage(GitCommitMessageHelperSettings settings, String authorName, String cardNumber, TypeAlias typeAlias, String shortDescription, String longDescription) {
        this.content = buildContent(
                settings,
                authorName,
                cardNumber,
                typeAlias,
                shortDescription,
                longDescription
        );
    }

    private String buildContent(GitCommitMessageHelperSettings settings,
                                String authorName,
                                String cardNumber,
                                TypeAlias typeAlias,
                                String shortDescription,
                                String longDescription
    ) {

        CommitTemplate commitTemplate = new CommitTemplate();
        if (StringUtils.isNotBlank(authorName)) {
            commitTemplate.setAuthorName(authorName);
        }
        if (StringUtils.isNotBlank(cardNumber)) {
            commitTemplate.setCardNumber(cardNumber);
        }
        if (typeAlias != null) {
            if (StringUtils.isNotBlank(typeAlias.getTitle())) {
                commitTemplate.setType(typeAlias.getTitle());
            }
        }
        if (StringUtils.isNotBlank(shortDescription)) {
            commitTemplate.setSubject(shortDescription);
        }
        if (StringUtils.isNotBlank(longDescription)) {
            commitTemplate.setBody(longDescription);
        }
        String template = settings.getDateSettings().getTemplate();
        return VelocityUtils.convert(template, commitTemplate);
    }

    @Override
    public String toString() {
        return content;
    }
}