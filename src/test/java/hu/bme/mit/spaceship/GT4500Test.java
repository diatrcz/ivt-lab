package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockedPrimary;  
  private TorpedoStore mockedSecondary;

  @BeforeEach
  public void init(){

  this.mockedPrimary = mock(TorpedoStore.class);
  this.mockedSecondary = mock(TorpedoStore.class);
    
  this.ship = new GT4500(mockedPrimary, mockedSecondary);
  
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockedPrimary.fire(1)).thenReturn(true);
    when(mockedSecondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockedPrimary, times(1)).fire(1);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_Fail_Primary(){
    // Arrange
    when(mockedPrimary.fire(1)).thenReturn(false);
    when(mockedSecondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockedPrimary, times(1)).fire(1);

    // Assert
    assertEquals(false, result);
  }

  @Test 
  public void fireTorpedo_Single_Secondary_Success(){
    // Arrange
    when(mockedPrimary.fire(1)).thenReturn(true);
    when(mockedSecondary.fire(1)).thenReturn(true);

    // Act
    boolean result;  
    result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockedPrimary, times(1)).fire(1);
    verify(mockedSecondary, times(1)).fire(1);

    // Assert
    assertEquals(true, result);
  }

  @Test 
  public void fireTorpedo_Single_Secondary_Fail(){
    // Arrange
    when(mockedPrimary.fire(1)).thenReturn(true);
    when(mockedSecondary.fire(1)).thenReturn(false);

    // Act
    boolean result;  
    result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockedPrimary, times(1)).fire(1);
    verify(mockedSecondary, times(1)).fire(1);

    // Assert
    assertEquals(false, result);
  }

  @Test 
  public void fireTorpedo_Single_Secondary_And_First_Empty(){
    // Arrange
    when(mockedPrimary.fire(1)).thenReturn(false);
    when(mockedSecondary.fire(1)).thenReturn(false);

    // Act
    boolean result;  
    result = ship.fireTorpedo(FiringMode.SINGLE);
    result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(mockedPrimary, times(1)).fire(1);
    verify(mockedSecondary, times(1)).fire(1);

    // Assert
    assertEquals(false, result);
  }



  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockedPrimary.fire(1)).thenReturn(true);
    when(mockedSecondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    verify(mockedPrimary, times(1)).fire(1);
    verify(mockedSecondary, times(1)).fire(1);

    // Assert
    assertEquals(true, result);
  }

}
