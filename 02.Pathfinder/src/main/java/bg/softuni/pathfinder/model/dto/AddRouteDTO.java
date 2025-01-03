package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.model.enums.Level;

public class AddRouteDTO {
    private String name;
    private String description;
    private Level level;
    ;
    private String videoUrl;

    public AddRouteDTO() {
    }

    public String getDescription() {
        return description;
    }

    public AddRouteDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public AddRouteDTO setLevel(Level level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public AddRouteDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public AddRouteDTO setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }
}
