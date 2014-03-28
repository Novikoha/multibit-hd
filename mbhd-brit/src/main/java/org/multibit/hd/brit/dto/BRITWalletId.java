package org.multibit.hd.brit.dto;

import com.google.bitcoin.core.ECKey;
import com.google.bitcoin.core.Utils;
import com.google.bitcoin.crypto.KeyCrypterScrypt;
import com.google.common.base.Preconditions;
import com.google.protobuf.ByteString;
import org.bitcoinj.wallet.Protos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongycastle.asn1.sec.SECNamedCurves;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.util.Arrays;

/**
 *  <p>Data object to provide the following to BRIT wallet seed related classes<br>
 *  <ul>
 *  <li>Creation of BRIT wallet id from seed</li>
 *  </ul>
 *  </p>
 *  
 */
public class BRITWalletId {

  private static final Logger log = LoggerFactory.getLogger(BRITWalletId.class);

  // The salt used in derivation of the britWalletId.
  // This is different from the similar process of deriving a WalletId (where the salt is 1)
  // This value is the 39,000,000th prime (http://primes.utm.edu/lists/small/millions/) which seemed like a nice number to use.
  private static final byte[] SALT_USED_IN_SCRYPT = BigInteger.valueOf(735_632_797).toByteArray();

  private final byte[] britWalletId;

  /**
   * Create a BRIT wallet id from the given seed.
   * This produces a BRIT wallet id from the seed using various trapdoor functions.
   * The seed is typically generated from the SeedPhraseGenerator#convertToSeed method.
   *
   * @param seed The seed to use in deriving the wallet id
   */
  public BRITWalletId(byte[] seed) {
    Preconditions.checkNotNull(seed);

    BigInteger seedBigInteger = new BigInteger(1, seed);

    // Convert the seed to a BRIT wallet id using various trapdoor functions.

    // Scrypt - scrypt is run using the seedBigInteger.toString() as the 'password'.
    // This returns a byte array (normally used as an AES256 key but here passed on to more trapdoor functions).
    // The scrypt parameters used are the default, with a salt of SALT_USED_IN_SCRYPT.
    Protos.ScryptParameters.Builder scryptParametersBuilder = Protos.ScryptParameters.newBuilder().setSalt(ByteString.copyFrom(SALT_USED_IN_SCRYPT));
    Protos.ScryptParameters scryptParameters = scryptParametersBuilder.build();
    KeyCrypterScrypt keyCrypterScrypt = new KeyCrypterScrypt(scryptParameters);
    KeyParameter keyParameter = keyCrypterScrypt.deriveKey(seedBigInteger.toString());
    byte[] derivedKey = keyParameter.getKey();
    //log.debug("derivedKey ='" + Utils.bytesToHexString(derivedKey) +  "'");

    // Ensure that the seed is within the Bitcoin EC group.
    X9ECParameters params = SECNamedCurves.getByName("secp256k1");
    BigInteger sizeOfGroup = params.getN();

    BigInteger derivedKeyBigInteger = new BigInteger(1, derivedKey);

    //log.debug("derivedKeyBigInteger (before) ='" + derivedKeyBigInteger +  "'");
    derivedKeyBigInteger = derivedKeyBigInteger.mod(sizeOfGroup);
    //log.debug("derivedKeyBigInteger (after) ='" + derivedKeyBigInteger +  "'");

    // EC curve generator function used to convert the key just derived (a 'private key') to a 'public key'
    ECPoint point = ECKey.CURVE.getG().multiply(derivedKeyBigInteger);
    // Note the public key is not compressed
    byte[] publicKey = point.getEncoded();
    //log.debug("publicKey ='" + Utils.bytesToHexString(publicKey) +  "'");

    // SHA256RIPE160 to generate final britWalletId bytes from the 'public key'
    britWalletId = Utils.sha256hash160(publicKey);

    //log.debug("britWalletId ='" + Utils.bytesToHexString(britWalletId) + "'");
  }

  public BRITWalletId(String britWalletIdInHex) {
    britWalletId = Utils.parseAsHexOrBase58(britWalletIdInHex);
  }


  /**
   * @return the raw wallet id as a byte[]
   */
  public byte[] getBytes() {
    return britWalletId;
  }


  @Override
  public String toString() {
    return Utils.bytesToHexString(britWalletId);

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BRITWalletId that = (BRITWalletId) o;

    if (!Arrays.equals(britWalletId, that.britWalletId)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return britWalletId != null ? Arrays.hashCode(britWalletId) : 0;
  }
}