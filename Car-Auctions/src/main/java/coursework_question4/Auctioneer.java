package coursework_question4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Auctioneer extends Dealership {
  private Map<Seller, Integer> sales;
  private Seller topSeller;

  public Auctioneer(String name) {
    super(name);
    sales = new HashMap<>();
    topSeller = new Seller("Null Null");
  }

  private boolean checkExistence(Car car) {
    for (Map.Entry<Advert, Seller> entry : carsForSale.entrySet()) {
      if (entry.getKey().getCar() == car) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String displaySoldCars() {
    StringBuffer sb = new StringBuffer();
    sb.append("SOLD CARS:\n");
    for (Map.Entry<Advert, Buyer> entry : soldCars.entrySet()) {
      sb.append(entry.getKey().getCar().getID() + " - Purchased by " + entry.getValue().toString()
          + " with a successful £"
          + String.format("%.2f", entry.getKey().getHighestOffer().getValue()) + " bid.\n");
    }
    return sb.toString();
  }

  @Override
  public String displayStatistics() {
    BufferedReader reader = null;
    StringBuffer display = new StringBuffer();

    display.append("** Auctioneer - " + name + "**\n");
    try {
      reader = new BufferedReader(new FileReader("auction_statistics.txt"));
      String line = reader.readLine();

      while (line != null) {
        display.append(line + "\n");
        line = reader.readLine();
      }
      display.setLength(display.length() - 1);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return display.toString();
  }

  private void updateStatistics(Seller seller) {
    seller.setSales(seller.getSales() + 1);

    if (sales.containsKey(seller)) {
      for (Map.Entry<Seller, Integer> entry : sales.entrySet()) {
        if (entry.getKey() == seller) {
          entry.setValue(entry.getValue() + 1);
        }
      }
    } else {
      sales.put(seller, seller.getSales());
    }

    for (Map.Entry<Seller, Integer> entry : sales.entrySet()) {
      if (entry.getValue() > topSeller.getSales() || topSeller == null) {
        topSeller = entry.getKey();
      }
    }
    
    int manual = 0;
    int automatic = 0;
    int totalSales = soldCars.size();

    for (Map.Entry<Advert, Buyer> entry : soldCars.entrySet()) {
      if (entry.getKey().getCar().getGearbox() == CarType.AUTOMATIC) {
        automatic++;
      } else {
        manual++;
      }
    }

    double percentMan = manual * 100 / totalSales;
    double percentAuto = automatic * 100 / totalSales;

    saveInFile(totalSales, percentMan, percentAuto);

  }

  private void saveInFile(int noOfSales, double percentageOfManual, double percentageOfAutomatic) {
    File file = new File("auction_statistics.txt");
    FileWriter fileWriter = null;

    try {
      fileWriter = new FileWriter(file);
      fileWriter.write("Total Auction Sales: " + noOfSales + "\n" + "Automatic Cars: "
          + percentageOfAutomatic + "%\n" + "Manual Cars: " + percentageOfManual + "%\n"
          + "Top Seller: " + topSeller.toString());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (fileWriter != null) {
        try {
          fileWriter.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  @Override
  public String displayUnsoldCars() {
    StringBuffer sb = new StringBuffer();
    sb.append("UNSOLD CARS:\n");
    for (Map.Entry<Advert, Seller> entry : unsoldCars.entrySet()) {
      sb.append(entry.getKey().toString());
    }
    return sb.toString();
  }

  public void endSale(Advert advert) {
    if (advert == null) {
      throw new IllegalArgumentException();
    }

    if (carsForSale.containsKey(advert)) {
      if (advert.getHighestOffer().getValue() >= advert.getCar().getPrice()) {
        soldCars.put(advert, advert.getHighestOffer().getBuyer());

        updateStatistics(carsForSale.get(advert));

        carsForSale.remove(advert);
      } else {
        unsoldCars.put(advert, carsForSale.get(advert));
        carsForSale.remove(advert);
      }
    }
  }

  @Override
  public boolean placeOffer(Advert carAdvert, User user, double value) {
    if (carAdvert == null || user == null) {
      throw new IllegalArgumentException();
    }
    if (!(carAdvert.getCar().getType() == SaleType.AUCTION)) {
      return false;
    }
    if (!checkExistence(carAdvert.getCar())) {
      return false;
    }
    carAdvert.placeOffer((Buyer) user, value);
    return true;
  }

  @Override
  public void registerCar(Advert carAdvert, User user, String colour, CarType type, CarBody body,
      int numberOfSeats) {
    if (carAdvert == null || user == null) {
      throw new IllegalArgumentException();
    }
    carAdvert.getCar().setGearbox(type);
    carAdvert.getCar().setColour(colour);
    carAdvert.getCar().setBody(body);
    carAdvert.getCar().setNumberOfSeats(numberOfSeats);
    
    if (!checkExistence(carAdvert.getCar())) {
      carsForSale.put(carAdvert, (Seller) user);
    }
  }
}
