package autobotzi.user.skill.utils;

public enum Experience {

    ZERO_TO_SIX_MONTHS("0-6 months"),
    SIX_TO_TWELVE_MONTHS("6-12 months"),
    ONE_TO_TWO_YEARS("1-2 years"),
    TWO_TO_THREE_YEARS("2-3 years"),
    THREE_TO_FOUR_YEARS("3-4 years"),
    FOUR_TO_FIVE_YEARS("4-7 years"),
    FIVE_TO_SEVEN_YEARS(">7 years");
    private final String experience;

    Experience(String experience) {
        this.experience = experience;
    }
    public String getExperience() {
        return experience;
    }
}
