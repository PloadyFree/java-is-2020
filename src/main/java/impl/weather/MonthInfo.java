package impl.weather;

import api.weather.DayTemperatureInfo;

import java.util.*;

public class MonthInfo {
    private Integer maxTemperature;
    private Double avgTemperature;

    private final Map<Integer, DayTemperatureInfo> daysInfo = new LinkedHashMap<>();

    public void updateMonthInfo(DayTemperatureInfo info) {
        int day = info.getDay();
        int temperature = info.getTemperature();

        daysInfo.put(day, info);

        if (maxTemperature == null || temperature > maxTemperature) {
            maxTemperature = temperature;
        }

        if (avgTemperature == null) {
            avgTemperature = (double) temperature;
        } else {
            int numberOfDays = daysInfo.size();
            avgTemperature = ((numberOfDays - 1) * avgTemperature + temperature) / numberOfDays;
        }

    }

    public Integer getMaxTemperature() {
        return maxTemperature;
    }

    public Double getAvgTemperature() {
        return avgTemperature;
    }

    public DayTemperatureInfo getDayInfo(int day) {
        return daysInfo.get(day);
    }

    public List<DayTemperatureInfo> getDaySortedByTemperature() {
        List<DayTemperatureInfo> list = new ArrayList<>(daysInfo.values());
        list.sort(java.util.Comparator.comparing(DayTemperatureInfo::getTemperature));
        return list;
    }
}
