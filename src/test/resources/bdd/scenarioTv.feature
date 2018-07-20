  Feature: Alpha-lab

    Scenario: televisions
      Given  moved to market
      When i move to electronics
      When i move to TV
      When i change num of items on page to 12
      When i send keys 20000 to min price filter
      When i click on LG and Samsung filters
      Then num of elements on tv page is 12 now
      When i get first item title
      When i search first item in search field
      Then titles is equals