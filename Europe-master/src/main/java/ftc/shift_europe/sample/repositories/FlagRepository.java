package ftc.shift_europe.sample.repositories;//import flag;
//import java.util.Collection;

import ftc.shift_europe.sample.models.Flag;

import java.util.List;

public interface FlagRepository {

    Flag fetchFlag(String flagName);

    Flag updateFlag(String userName, String routeName, String flagName, Flag flag);

    void deleteFlag(String flagName);

    Flag createFlag(String userName, String routeName, Flag flag);

    List<Flag> getAllFlags(String routeName);
}