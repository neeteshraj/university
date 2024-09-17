package main.interfaces;

import java.util.List;

public interface  ComplaintInterface{
    void submitComplaint(String email, String description);
    List<String> viewComplaints(String email);
}