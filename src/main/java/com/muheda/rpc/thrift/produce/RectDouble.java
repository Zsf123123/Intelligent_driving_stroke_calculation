package com.muheda.rpc.thrift.produce;

/**
 * Autogenerated by Thrift Compiler (0.12.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.12.0)", date = "2019-07-04")
public class RectDouble implements org.apache.thrift.TBase<RectDouble, RectDouble._Fields>, java.io.Serializable, Cloneable, Comparable<RectDouble> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RectDouble");

  private static final org.apache.thrift.protocol.TField LB_X_FIELD_DESC = new org.apache.thrift.protocol.TField("lbX", org.apache.thrift.protocol.TType.DOUBLE, (short)1);
  private static final org.apache.thrift.protocol.TField LB_Y_FIELD_DESC = new org.apache.thrift.protocol.TField("lbY", org.apache.thrift.protocol.TType.DOUBLE, (short)2);
  private static final org.apache.thrift.protocol.TField RT_X_FIELD_DESC = new org.apache.thrift.protocol.TField("rtX", org.apache.thrift.protocol.TType.DOUBLE, (short)3);
  private static final org.apache.thrift.protocol.TField RT_Y_FIELD_DESC = new org.apache.thrift.protocol.TField("rtY", org.apache.thrift.protocol.TType.DOUBLE, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RectDoubleStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RectDoubleTupleSchemeFactory();

  public double lbX; // required
  public double lbY; // required
  public double rtX; // required
  public double rtY; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    LB_X((short)1, "lbX"),
    LB_Y((short)2, "lbY"),
    RT_X((short)3, "rtX"),
    RT_Y((short)4, "rtY");

    private static final java.util.Map<String, _Fields> byName = new java.util.HashMap<String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // LB_X
          return LB_X;
        case 2: // LB_Y
          return LB_Y;
        case 3: // RT_X
          return RT_X;
        case 4: // RT_Y
          return RT_Y;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __LBX_ISSET_ID = 0;
  private static final int __LBY_ISSET_ID = 1;
  private static final int __RTX_ISSET_ID = 2;
  private static final int __RTY_ISSET_ID = 3;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.LB_X, new org.apache.thrift.meta_data.FieldMetaData("lbX", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.LB_Y, new org.apache.thrift.meta_data.FieldMetaData("lbY", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.RT_X, new org.apache.thrift.meta_data.FieldMetaData("rtX", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.RT_Y, new org.apache.thrift.meta_data.FieldMetaData("rtY", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RectDouble.class, metaDataMap);
  }

  public RectDouble() {
  }

  public RectDouble(
    double lbX,
    double lbY,
    double rtX,
    double rtY)
  {
    this();
    this.lbX = lbX;
    setLbXIsSet(true);
    this.lbY = lbY;
    setLbYIsSet(true);
    this.rtX = rtX;
    setRtXIsSet(true);
    this.rtY = rtY;
    setRtYIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RectDouble(RectDouble other) {
    __isset_bitfield = other.__isset_bitfield;
    this.lbX = other.lbX;
    this.lbY = other.lbY;
    this.rtX = other.rtX;
    this.rtY = other.rtY;
  }

  public RectDouble deepCopy() {
    return new RectDouble(this);
  }

  @Override
  public void clear() {
    setLbXIsSet(false);
    this.lbX = 0.0;
    setLbYIsSet(false);
    this.lbY = 0.0;
    setRtXIsSet(false);
    this.rtX = 0.0;
    setRtYIsSet(false);
    this.rtY = 0.0;
  }

  public double getLbX() {
    return this.lbX;
  }

  public RectDouble setLbX(double lbX) {
    this.lbX = lbX;
    setLbXIsSet(true);
    return this;
  }

  public void unsetLbX() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __LBX_ISSET_ID);
  }

  /** Returns true if field lbX is set (has been assigned a value) and false otherwise */
  public boolean isSetLbX() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __LBX_ISSET_ID);
  }

  public void setLbXIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __LBX_ISSET_ID, value);
  }

  public double getLbY() {
    return this.lbY;
  }

  public RectDouble setLbY(double lbY) {
    this.lbY = lbY;
    setLbYIsSet(true);
    return this;
  }

  public void unsetLbY() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __LBY_ISSET_ID);
  }

  /** Returns true if field lbY is set (has been assigned a value) and false otherwise */
  public boolean isSetLbY() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __LBY_ISSET_ID);
  }

  public void setLbYIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __LBY_ISSET_ID, value);
  }

  public double getRtX() {
    return this.rtX;
  }

  public RectDouble setRtX(double rtX) {
    this.rtX = rtX;
    setRtXIsSet(true);
    return this;
  }

  public void unsetRtX() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __RTX_ISSET_ID);
  }

  /** Returns true if field rtX is set (has been assigned a value) and false otherwise */
  public boolean isSetRtX() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __RTX_ISSET_ID);
  }

  public void setRtXIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __RTX_ISSET_ID, value);
  }

  public double getRtY() {
    return this.rtY;
  }

  public RectDouble setRtY(double rtY) {
    this.rtY = rtY;
    setRtYIsSet(true);
    return this;
  }

  public void unsetRtY() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __RTY_ISSET_ID);
  }

  /** Returns true if field rtY is set (has been assigned a value) and false otherwise */
  public boolean isSetRtY() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __RTY_ISSET_ID);
  }

  public void setRtYIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __RTY_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable Object value) {
    switch (field) {
    case LB_X:
      if (value == null) {
        unsetLbX();
      } else {
        setLbX((Double)value);
      }
      break;

    case LB_Y:
      if (value == null) {
        unsetLbY();
      } else {
        setLbY((Double)value);
      }
      break;

    case RT_X:
      if (value == null) {
        unsetRtX();
      } else {
        setRtX((Double)value);
      }
      break;

    case RT_Y:
      if (value == null) {
        unsetRtY();
      } else {
        setRtY((Double)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public Object getFieldValue(_Fields field) {
    switch (field) {
    case LB_X:
      return getLbX();

    case LB_Y:
      return getLbY();

    case RT_X:
      return getRtX();

    case RT_Y:
      return getRtY();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case LB_X:
      return isSetLbX();
    case LB_Y:
      return isSetLbY();
    case RT_X:
      return isSetRtX();
    case RT_Y:
      return isSetRtY();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof RectDouble)
      return this.equals((RectDouble)that);
    return false;
  }

  public boolean equals(RectDouble that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_lbX = true;
    boolean that_present_lbX = true;
    if (this_present_lbX || that_present_lbX) {
      if (!(this_present_lbX && that_present_lbX))
        return false;
      if (this.lbX != that.lbX)
        return false;
    }

    boolean this_present_lbY = true;
    boolean that_present_lbY = true;
    if (this_present_lbY || that_present_lbY) {
      if (!(this_present_lbY && that_present_lbY))
        return false;
      if (this.lbY != that.lbY)
        return false;
    }

    boolean this_present_rtX = true;
    boolean that_present_rtX = true;
    if (this_present_rtX || that_present_rtX) {
      if (!(this_present_rtX && that_present_rtX))
        return false;
      if (this.rtX != that.rtX)
        return false;
    }

    boolean this_present_rtY = true;
    boolean that_present_rtY = true;
    if (this_present_rtY || that_present_rtY) {
      if (!(this_present_rtY && that_present_rtY))
        return false;
      if (this.rtY != that.rtY)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(lbX);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(lbY);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(rtX);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(rtY);

    return hashCode;
  }

  @Override
  public int compareTo(RectDouble other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetLbX()).compareTo(other.isSetLbX());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLbX()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.lbX, other.lbX);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLbY()).compareTo(other.isSetLbY());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLbY()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.lbY, other.lbY);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRtX()).compareTo(other.isSetRtX());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRtX()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.rtX, other.rtX);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRtY()).compareTo(other.isSetRtY());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRtY()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.rtY, other.rtY);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("RectDouble(");
    boolean first = true;

    sb.append("lbX:");
    sb.append(this.lbX);
    first = false;
    if (!first) sb.append(", ");
    sb.append("lbY:");
    sb.append(this.lbY);
    first = false;
    if (!first) sb.append(", ");
    sb.append("rtX:");
    sb.append(this.rtX);
    first = false;
    if (!first) sb.append(", ");
    sb.append("rtY:");
    sb.append(this.rtY);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'lbX' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'lbY' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'rtX' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'rtY' because it's a primitive and you chose the non-beans generator.
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RectDoubleStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RectDoubleStandardScheme getScheme() {
      return new RectDoubleStandardScheme();
    }
  }

  private static class RectDoubleStandardScheme extends org.apache.thrift.scheme.StandardScheme<RectDouble> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RectDouble struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // LB_X
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.lbX = iprot.readDouble();
              struct.setLbXIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // LB_Y
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.lbY = iprot.readDouble();
              struct.setLbYIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // RT_X
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.rtX = iprot.readDouble();
              struct.setRtXIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // RT_Y
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.rtY = iprot.readDouble();
              struct.setRtYIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetLbX()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'lbX' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetLbY()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'lbY' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetRtX()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'rtX' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetRtY()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'rtY' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, RectDouble struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(LB_X_FIELD_DESC);
      oprot.writeDouble(struct.lbX);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(LB_Y_FIELD_DESC);
      oprot.writeDouble(struct.lbY);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(RT_X_FIELD_DESC);
      oprot.writeDouble(struct.rtX);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(RT_Y_FIELD_DESC);
      oprot.writeDouble(struct.rtY);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RectDoubleTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RectDoubleTupleScheme getScheme() {
      return new RectDoubleTupleScheme();
    }
  }

  private static class RectDoubleTupleScheme extends org.apache.thrift.scheme.TupleScheme<RectDouble> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RectDouble struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeDouble(struct.lbX);
      oprot.writeDouble(struct.lbY);
      oprot.writeDouble(struct.rtX);
      oprot.writeDouble(struct.rtY);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RectDouble struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.lbX = iprot.readDouble();
      struct.setLbXIsSet(true);
      struct.lbY = iprot.readDouble();
      struct.setLbYIsSet(true);
      struct.rtX = iprot.readDouble();
      struct.setRtXIsSet(true);
      struct.rtY = iprot.readDouble();
      struct.setRtYIsSet(true);
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

