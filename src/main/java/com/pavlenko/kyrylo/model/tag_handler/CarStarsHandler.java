package com.pavlenko.kyrylo.model.tag_handler;

import com.pavlenko.kyrylo.model.entity.Quality;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.stream.IntStream;

public class CarStarsHandler extends TagSupport {

    private static final String STAR_UNICODE = "&#9733;";

    private String carQuality;

    @Override
    public int doStartTag() throws JspException {
        StringBuilder stars = new StringBuilder();
        JspWriter out = pageContext.getOut();

        Quality.QualityEnum qualityEnum = Quality.QualityEnum.valueOf(carQuality);

        int starNumber = qualityEnum.getStarNumber();
        IntStream.range(0, starNumber).forEach(val -> stars.append(STAR_UNICODE));

        try {
            out.print(stars);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return SKIP_BODY;
    }

    public void setCarQuality(String quality) {this.carQuality = quality;}
}
