package parser;

import command.Command;
import command.LocationCommand;
import exception.DukeException;

public class LocationParser extends IndexParser {

    public LocationParser(String userInput, String command) {
        super(userInput, command);
    }

    @Override
    public Command parse() throws DukeException {
        super.extract();
        String locationString = extractNewLocation(taskFeatures);
        return new LocationCommand(indexOfTask, locationString);
    }

    private String extractNewLocation(String taskFeatures) throws DukeException {
        String locationString;
        try {
            String[] locationCommandParts = taskFeatures.split("\\s+", 2);
            locationString = locationCommandParts[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(DukeException.invalidLocation());
        }
        return locationString;
    }
}
