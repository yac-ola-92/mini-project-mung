package com.example.mung.domain.transfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

@Getter
@Setter
@AllArgsConstructor
public class PetInfo {
    private JSONObject pet_info;

    public PetInfo() {
        this.pet_info = new JSONObject();
    }

    public PetInfo(String jsonString) {
        this.pet_info = new JSONObject(jsonString);
    }

    public JSONObject getPetInfo() {
        return pet_info;
    }

    public void setPetInfoFromString(String jsonString) {
        this.pet_info = new JSONObject(jsonString);
    }

    public String getPetInfoAsString() {
        return this.pet_info.toString();
    }

    @Override
    public String toString() {
        return pet_info.toString();
    }
}
