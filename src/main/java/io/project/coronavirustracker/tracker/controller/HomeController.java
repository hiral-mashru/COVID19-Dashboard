package io.project.coronavirustracker.tracker.controller;

import io.project.coronavirustracker.tracker.model.Deaths;
import io.project.coronavirustracker.tracker.model.LocationStats;
import io.project.coronavirustracker.tracker.model.RecoveryCases;
import io.project.coronavirustracker.tracker.model.Resultant;
import io.project.coronavirustracker.tracker.service.DataSevice;
//import org.json.JSONArray;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class HomeController {

    @Autowired
    DataSevice dataSevice;

    public HashSet<String> getCountrySet() {
        List<Deaths> deaths = dataSevice.getDeath();

        HashSet<String> countryset = new HashSet<>();
        for (Deaths dett : deaths) {
            countryset.add(dett.getCountry());
        }
        return countryset;
    }

    public JSONArray getJSONArray1() {
        List<Deaths> deaths = dataSevice.getDeath();

        JSONArray jsonArray = new JSONArray();
        int i = 0;
        for (Deaths dett : deaths) {
            Resultant resultant = new Resultant();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("country", dett.getCountry());
            resultant.setCountry(dett.getCountry());
            jsonObject.put("state", dett.getState());
            jsonObject.put("death", dett.getDeath());
            resultant.setCountryDeath(dett.getDeath());
            jsonArray.put(i, jsonObject);
            i++;
        }
        System.out.println("array: " + jsonArray);
        return jsonArray;
    }

    public JSONArray getJSONArray2() {
        List<RecoveryCases> recovery = dataSevice.getNewRecovery();
        JSONArray jsonArray2 = new JSONArray();
        int j = 0;
        for (RecoveryCases recovry : recovery) {
            Resultant resultant = new Resultant();
            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("country", recovry.getCountry());
            jsonObject2.put("state", recovry.getState());
            jsonObject2.put("recovery", recovry.getRecovery());
            resultant.setCountryRecovery(recovry.getRecovery());
            jsonArray2.put(j, jsonObject2);
            j++;
        }
        System.out.println("array2: " + jsonArray2);
        return jsonArray2;
    }

    public JSONArray getJSONArray3() {
        List<LocationStats> allStats = dataSevice.getAllStats();

        JSONArray jsonArray3 = new JSONArray();
        int k = 0;
        for (LocationStats confirm : allStats) {
            Resultant resultant = new Resultant();
            JSONObject jsonObject3 = new JSONObject();
            jsonObject3.put("country", confirm.getCountry());
            jsonObject3.put("state", confirm.getState());
            jsonObject3.put("confirm", confirm.getLatestTotalCases());
            resultant.setCountryConfirm(confirm.getLatestTotalCases());
            jsonArray3.put(k, jsonObject3);
            k++;
        }
        System.out.println("array3: " + jsonArray3);
        return jsonArray3;
    }

    public JSONArray getJSONArray4(){
        List<LocationStats> allStats = dataSevice.getAllStats();

        JSONArray jsonArray4 = new JSONArray();
        int l = 0;
        for (LocationStats change : allStats) {
            JSONObject jsonObject4 = new JSONObject();
            jsonObject4.put("country", change.getCountry());
            jsonObject4.put("state", change.getState());
            jsonObject4.put("changes", change.getDiffFromPrevDay());
            jsonArray4.put(l, jsonObject4);
            l++;
        }
        System.out.println("array4: " + jsonArray4);
        return jsonArray4;
    }

    public HashMap<String, Integer> getCountryDeath() {

        JSONArray jsonArray = getJSONArray1();
        HashSet<String> countryset = getCountrySet();
        HashMap<String, Integer> countrydeath = new HashMap<>();
        int total;
        JSONObject jsonObject1;
        for (String cntry : countryset) {
            total = 0;
            for (int j = 0; j < jsonArray.length(); j++) {
                jsonObject1 = jsonArray.getJSONObject(j);
                if (cntry.equals(jsonObject1.optString("country"))) {
                    total = total + jsonObject1.optInt("death");
                }
            }
            countrydeath.put(cntry, total);
        }
        System.out.println(" in countrydeath: " + countrydeath);
        return countrydeath;
    }

    public HashMap<String, Integer> getCountryRecovery() {
        JSONArray jsonArray2 = getJSONArray2();
        HashSet<String> countryset = getCountrySet();
        HashMap<String, Integer> countryrecovery = new HashMap<>();
        int total2;
        JSONObject jsonObject2;
        for (String cntry : countryset) {
            total2 = 0;
            for (int k = 0; k < jsonArray2.length(); k++) {
                jsonObject2 = jsonArray2.getJSONObject(k);
                if (cntry.equals(jsonObject2.optString("country"))) {
                    total2 = total2 + jsonObject2.optInt("recovery");
                }
            }
            countryrecovery.put(cntry, total2);
        }
        System.out.println("in countryrecovery: " + countryrecovery);
        return countryrecovery;
    }

    public HashMap<String, Integer> getCountryConfirm() {
        JSONArray jsonArray3 = getJSONArray3();
        HashSet<String> countryset = getCountrySet();
        HashMap<String, Integer> countryconfirm = new HashMap<>();
        int total3;
        JSONObject jsonObject3;
        for (String cntry : countryset) {
            total3 = 0;
            for (int l = 0; l < jsonArray3.length(); l++) {
                jsonObject3 = jsonArray3.getJSONObject(l);
                if (cntry.equals(jsonObject3.optString("country"))) {
                    total3 = total3 + jsonObject3.optInt("confirm");
                }
            }
            countryconfirm.put(cntry, total3);
        }
        System.out.println("innn countryconfirm: " + countryconfirm);
        return countryconfirm;
    }

    public HashMap<String, Integer> getCountryChanges() {
        JSONArray jsonArray4 = getJSONArray4();
        HashSet<String> countryset = getCountrySet();
        HashMap<String, Integer> countrychange = new HashMap<>();
        int total4;
        JSONObject jsonObject4;
        for (String cntry : countryset) {
            total4 = 0;
            for (int m = 0; m < jsonArray4.length(); m++) {
                jsonObject4 = jsonArray4.getJSONObject(m);
                if (cntry.equals(jsonObject4.optString("country"))) {
                    total4 = total4 + jsonObject4.optInt("changes");
                }
            }
            countrychange.put(cntry, total4);
        }
        System.out.println("inn countrychange: " + countrychange);
        return countrychange;
    }

    @GetMapping("/")
    public String home(Model model) throws Exception {
        List<LocationStats> allStats = dataSevice.getAllStats();
        List<Deaths> deaths = dataSevice.getDeath();
        List<RecoveryCases> recovery = dataSevice.getNewRecovery();

        int totalReportedCases = allStats.stream().mapToInt(state -> state.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        int totaldeath = deaths.stream().mapToInt(stat -> stat.getDeath()).sum();
        int totalrecovered = recovery.stream().mapToInt(stat -> stat.getRecovery()).sum();
        int totalactive = totalReportedCases - totaldeath - totalrecovered;

        model.addAttribute("countrychange", getCountryChanges());
        model.addAttribute("countryconfirm", getCountryConfirm());
        model.addAttribute("countryrecovery", getCountryRecovery());
        model.addAttribute("contrydeath", getCountryDeath());
        model.addAttribute("deaths", deaths);
        model.addAttribute("locationStats", allStats);
        model.addAttribute("recovery", recovery);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        model.addAttribute("totalDeaths", totaldeath);
        model.addAttribute("totalrecovered", totalrecovered);
        model.addAttribute("totalActive", totalactive);
        return "home";
    }

    @GetMapping("/chart")
    public String graph(Model model) {
        return "chart";
    }

    @GetMapping("/deathGraph")
    @ResponseBody
    public ResponseEntity<?> getDeathGraph() {
        HashMap<String, Integer> deathGraph = getCountryDeath();
        System.out.println("tostring: " + deathGraph.toString());
        System.out.println("deathgraphdata: " + deathGraph);
        return new ResponseEntity<>(deathGraph, HttpStatus.OK);
    }

    @GetMapping("/recoveryGraph")
    @ResponseBody
    public ResponseEntity<?> getRecoveryGraph() {
        HashMap<String, Integer> recoveryGraph = getCountryRecovery();
        System.out.println("recoverygraphdata: " + recoveryGraph);
        return new ResponseEntity<>(recoveryGraph, HttpStatus.OK);
    }

    @GetMapping("/confirmGraph")
    @ResponseBody
    public ResponseEntity<?> getConfirmGraph() {
        HashMap<String, Integer> confirmGraph = getCountryRecovery();
        System.out.println("confirmgraphdata: " + confirmGraph);
        return new ResponseEntity<>(confirmGraph, HttpStatus.OK);
    }

    public JSONObject jsonObject = new JSONObject();
    public List<Resultant> resultant = new ArrayList<>();
    public List<Resultant> getResultant(){ return resultant; }

    @GetMapping("/{country}")
    public String states(Model model, @PathVariable String country) {
        System.out.println("get cntry" + country);
        List<LocationStats> allstats = dataSevice.getAllStats();
        List<Deaths> deaths = dataSevice.getDeath();
        List<RecoveryCases> recoveryCases = dataSevice.getNewRecovery();
        List<Resultant> newresult = new ArrayList<>();

        List<String> stat = new ArrayList<>();
        List<Integer> deathStat = new ArrayList<>();
        List<Integer> recoveryStat = new ArrayList<>();
        List<Integer> confirmStat = new ArrayList<>();
        List<Integer> changeStat = new ArrayList<>();

        JSONArray states = new JSONArray();
        JSONArray deathState = new JSONArray();
        JSONArray recoveryState = new JSONArray();
        JSONArray confirmState = new JSONArray();
        JSONArray changeState = new JSONArray();



        for (LocationStats stats : allstats) {
            Resultant result = new Resultant();
            if (country.equals(stats.getCountry())) {
                if (stats.getState().equals("")) {
                    getNull();
                    stat.add("");
                    confirmStat.add(-1);
                    changeStat.add(-1);
                } else {
//                    stateConfirm(country);
                    stat.add(stats.getState());
                    states.put(stats.getState());
                    result.setState(stats.getState());
                    confirmStat.add(stats.getLatestTotalCases());
                    confirmState.put(stats.getLatestTotalCases());
                    result.setCountryConfirm(stats.getLatestTotalCases());
                    changeStat.add(stats.getDiffFromPrevDay());
                    changeState.put(stats.getDiffFromPrevDay());
                    result.setChanges(stats.getDiffFromPrevDay());
                }
            }
            newresult.add(result);
        }
        this.resultant = newresult;
        for (Deaths stats : deaths) {
            Resultant result = new Resultant();
            if (country.equals(stats.getCountry())) {
                if (stats.getState().equals("")) {
                    getNull();
                    deathStat.add(-1);
                } else {
//                    stateConfirm(country);
                    deathStat.add(stats.getDeath());
                    deathState.put(stats.getDeath());
                    result.setCountryDeath(stats.getDeath());
                }
                newresult.add(result);
            }
        }
        this.resultant = newresult;
            for (RecoveryCases stats : recoveryCases) {
                Resultant result = new Resultant();
                if (country.equals(stats.getCountry())) {
                    if (stats.getState().equals("")) {
                        getNull();
                        recoveryStat.add(-1);
                    } else {
//                        stateConfirm(country);
                        recoveryStat.add(stats.getRecovery());
                        recoveryState.put(stats.getRecovery());
                        result.setCountryRecovery(stats.getRecovery());
                    }
                    newresult.add(result);
                }
            }
            this.resultant = newresult;
            System.out.println("country: " + country + "states: " + states);
            System.out.println("deaths: " + deathState);
            System.out.println("recovery: " + recoveryState);

            jsonObject.put("states",states);
            jsonObject.put("confirm",confirmState);
            jsonObject.put("change",changeState);
            jsonObject.put("death",deathState);
            jsonObject.put("recovery",recoveryState);

            model.addAttribute("country",country);
            model.addAttribute("states", stat);
            model.addAttribute("deaths", deathStat);
            model.addAttribute("recovery", recoveryStat);
            model.addAttribute("confirm", confirmStat);
            model.addAttribute("changes", changeStat);

            return "states";
        }

        public void getNull(){
            System.out.println("Data Unavailable");
        }

        @GetMapping("stateChart")
        @ResponseBody
        public ResponseEntity<?> stateConfirm(){
//            System.out.println(country);
            List<LocationStats> allstats = dataSevice.getAllStats();
            JSONArray jsonState = new JSONArray();
            JSONArray jsondeath = new JSONArray();
            JSONObject obj = new JSONObject();
            allstats.forEach(stat-> {
                jsonState.put(stat.getState());
                jsondeath.put(stat.getTotalDeaths());
            });

            return new ResponseEntity<>(allstats,HttpStatus.OK);
        }

    @GetMapping("/sort")
    public String sort(Model model){
        HashMap<String,Integer> deathsort = getCountryDeath();
        List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(deathsort.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {
                return (o2.getValue().compareTo(o1.getValue()));
            }
        });

        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }

        System.out.println("sort: "+temp);

        HashMap<String, Integer> final1 = new LinkedHashMap<String, Integer>();
        Set<String> keys = temp.keySet();
        String[] keysArray = keys.toArray(new String[keys.size()]);
        for(int i=0; i<keysArray.length && i<10;i++) {
            System.out.println("top10: "+temp.get(keysArray[i])+" "+keysArray[i]);
            final1.put(keysArray[i],temp.get(keysArray[i]));
        }

        model.addAttribute("deathsort",final1);
//------------------------------------------------------------------------
        HashMap<String,Integer> recoverysort = getCountryRecovery();
        List<Map.Entry<String, Integer> > list2 = new LinkedList<Map.Entry<String, Integer> >(recoverysort.entrySet());

        Collections.sort(list2, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {
                return (o2.getValue().compareTo(o1.getValue()));
            }
        });

        HashMap<String, Integer> temp2 = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list2) {
            temp2.put(aa.getKey(), aa.getValue());
        }

        System.out.println("recoverysort: "+temp2);

        HashMap<String, Integer> final2 = new LinkedHashMap<String, Integer>();
        Set<String> keys2 = temp2.keySet();
        String[] keysArray2 = keys2.toArray(new String[keys2.size()]);
        for(int i=0; i<keysArray2.length && i<10;i++) {
            System.out.println("top10: "+temp2.get(keysArray2[i])+" "+keysArray2[i]);
            final2.put(keysArray2[i],temp2.get(keysArray2[i]));
        }

        model.addAttribute("recoverysort",final2);
//        --------------------------------------------------------------
        return "sort";
    }

    }
