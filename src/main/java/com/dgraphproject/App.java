package com.dgraphproject;

import java.io.IOException;
import java.net.MalformedURLException;

import com.dgraphproject.services.AchivementService;
import com.dgraphproject.services.HttpServiceCustom;
import com.dgraphproject.utils.DPConnectionUtils;

import io.dgraph.DgraphClient;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException
    {
        DPConnectionUtils dpConnectionUtils = new DPConnectionUtils();
        try {
            DgraphClient dgraphClient = dpConnectionUtils.connectDb();

            HttpServiceCustom httpServiceCustom = new HttpServiceCustom();

            AchivementService achivementService = new AchivementService(httpServiceCustom);

            // achivementService.getAllAchievements(dgraphClient);

            achivementService.createAchievement("Test2", "description 4", "url/to/logo");

            System.out.println("------------ Before Update ------------");

            achivementService.getAllAchievements();

            achivementService.updateAchievement("0x1a4fda0129", "updated 3", "updated 3", "upadted 2");

            System.out.println("------------ After Update ------------");

            achivementService.getAllAchievements();

            System.out.println("------------ After Delete ------------");

            achivementService.deleteAchievements("0x1a4fda0129");
            achivementService.deleteAllAchievements();
            achivementService.getAllAchievements();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
