package com.pavlenko.kyrylo.model.tag_handler;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;

public class EmptyTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            out.print(LocalDateTime.now());
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}
