package edu.temple.howcanihelpapp.Firebase.DatabaseItems;

import java.util.HashMap;
import java.util.Map;

public class HelpListingUpdate {
    public interface UpdateBuilder {
        void builder(HelpListingBuilder update);
    }

    private HelpListingBuilder helpListingBuilder;
    private HelpListing oldValue;
    public HelpListingUpdate(HelpListing oldValue) {
        // One is cloned so that a difference can be noted when one is modified.
        this.helpListingBuilder = new HelpListingBuilder(oldValue.clone());
        this.oldValue = oldValue;
    }

    public HelpListingUpdate update(UpdateBuilder updateBuilder) {
        updateBuilder.builder(this.helpListingBuilder);
        return this;
    }

    /**
     *
     * @return The the updated values in a hash map.
     */
    public Map<String, Object> getHelpListingUpdatedValues() {
        HelpListing helpListing = helpListingBuilder.getHelpListing();
        Map<String, Object> updatedValues = new HashMap<>();
        if(helpListing.description != oldValue.description)
            updatedValues.put("description", helpListing.description);
        if(helpListing.title != oldValue.title)
            updatedValues.put("title", helpListing.title);
        if(helpListing.canRelocate != oldValue.canRelocate)
            updatedValues.put("canRelocate", helpListing.canRelocate);
        if(helpListing.isRequest != oldValue.isRequest)
            updatedValues.put("isRequest", helpListing.isRequest);
        if(helpListing.isUrgent != oldValue.isUrgent)
            updatedValues.put("isUrgent", helpListing.isUrgent);
        if(helpListing.location != oldValue.location)
            updatedValues.put("location", helpListing.location);

        return updatedValues;
    }
}
