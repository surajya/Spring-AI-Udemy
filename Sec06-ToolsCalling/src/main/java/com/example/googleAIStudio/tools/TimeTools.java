package com.example.googleAIStudio.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class TimeTools {

    private static final Logger logger = LoggerFactory.getLogger(TimeTools.class);

    @Tool(
            name = "getLocalDateTime",
            description = "Find the local date time in user location"
    )
    public String getLocalDateTime() {
        logger.info("getLocalDateTime of user location");
        return LocalDateTime.now().toString();
    }

    @Tool(
            name = "getSpecificZoneIdDateTime",
            description = "Find the date time for the Specific Zone id"
    )
    public String getSpecificZoneIdDateTime(@ToolParam(
            description = "value representing the time Zone") String zoneId
    ) {
        logger.info("getDateTime for the Zone id {}", zoneId);
        return LocalDateTime.now(ZoneId.of(zoneId)).toString();
    }
}
