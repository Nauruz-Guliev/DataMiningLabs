# :star: Bloom Filter 

Bloom filter algorithm that tells wether a word is present in a text or not. This project contains non-bloom filter related files that can be ignored. 
The bloom filter itself and all related code can be found [here](https://github.com/Nauruz-Guliev/lab-bloom-filter/tree/master/src/main/java/ru/kpfu/itis/gnt/hwpebble/bloomfilter).  

By default false positive rate is set to 0.5 

### 🛠️ Details


##### How hash functions are being created.
```Java
 private int[] getHashValues(String input) {
        int[] hashValues = new int[numHashFunctions];
        int hash1 = input.hashCode();
        int hash2 = new Random(hash1).nextInt();
        for (int i = 0; i < numHashFunctions; i++) {
            hashValues[i] = Math.abs((hash1 + i * hash2) % bitSetSize);
        }
        return hashValues;
 }
```

##### Bitset size is calculated according to the formula:

$\-((n*lnp)/(ln2)^2)$

```Java
  int bitSetSize = (int) Math.ceil(-1 * (expectedNumEntries * Math.log(falsePositiveRate)) /
                Math.pow(Math.log(2.0), 2.0));

```

##### Amount of hashfunctions that are needed:
$\ m/n * ln(2)$
```Java
   this.numHashFunctions = (int) Math.round((bitSetSize / (double) expectedNumEntries) * Math.log(2.0));
```

### 🖼️ Screenshots

###### Positive result
<p align="left">
  <img src="../master/images/positive.png" width="800"/>
</p>

###### Negative result

<p align="left">
  <img src="../master/images/negative.png" width="800"/>
</p>

###### False positive result

<p align="left">
  <img src="../master/images/false_positive.png" width="800"/>
</p>

