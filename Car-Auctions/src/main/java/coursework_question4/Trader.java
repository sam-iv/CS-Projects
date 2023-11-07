package coursework_question4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trader extends Dealership {
  private List<Seller> sellers;

  public Trader(String name) {
    super(name);
    sellers = new ArrayList<>();
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

    display.append("** Trader - " + name + "**\n");
    try {
      reader = new BufferedReader(new FileReader("trade_statistics.txt"));
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

  public void updateStatistics(Seller seller) {
    seller.setSales(seller.getSales() + 1);

    if (!sellers.contains(seller)) {
      sellers.add(seller);
    }
    saveInFile(soldCars.size());
  }

  private void saveInFile(int noOfSales) {
    File file = new File("trade_statistics.txt");
    FileWriter fileWriter = null;

    try {
      fileWriter = new FileWriter(file);
      fileWriter.write("Total Sales: " + noOfSales + "\n" + "All Sellers:\n");
      for (Seller seller : sellers) {
        fileWriter.write("\t" + seller.toString());
        if (!(sellers.indexOf(seller) == sellers.size() - 1)) {
          fileWriter.write("\n");
        }
      }
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

  private void endSale(Advert advert) {
    if (advert == null) {
      throw new IllegalArgumentException();
    }
    
    if (carsForSale.containsKey(advert)) {
      if (advert.getHighestOffer().getValue() >= advert.getCar().getPrice()) {
        soldCars.put(advert, advert.getHighestOffer().getBuyer());
        updateStatistics(carsForSale.get(advert));
        carsForSale.remove(advert);
        unsoldCars.remove(advert);
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
    if (!(carAdvert.getCar().getType() == SaleType.FORSALE)) {
      return false;
    }
    if (!checkExistence(carAdvert.getCar())) {
      return false;
    }
    if (value >= carAdvert.getCar().getPrice()) {
      carAdvert.placeOffer((Buyer) user, value);
      endSale(carAdvert);
      return true;

    } else {
      carAdvert.placeOffer((Buyer) user, value);
      return false;
    }
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
      unsoldCars.put(carAdvert, (Seller) user);
    }
  }
}
