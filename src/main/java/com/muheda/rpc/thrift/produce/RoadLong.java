package com.muheda.rpc.thrift.produce;

/**
 * Autogenerated by Thrift Compiler (0.12.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.12.0)", date = "2019-06-27")
public class RoadLong implements org.apache.thrift.TBase<RoadLong, RoadLong._Fields>, java.io.Serializable, Cloneable, Comparable<RoadLong> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RoadLong");

  private static final org.apache.thrift.protocol.TField ROAD_LEVEL_FIELD_DESC = new org.apache.thrift.protocol.TField("roadLevel", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField PTS_FIELD_DESC = new org.apache.thrift.protocol.TField("pts", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RoadLongStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RoadLongTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable String roadLevel; // required
  public @org.apache.thrift.annotation.Nullable java.util.List<PointLong> pts; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ROAD_LEVEL((short)1, "roadLevel"),
    PTS((short)2, "pts");

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
        case 1: // ROAD_LEVEL
          return ROAD_LEVEL;
        case 2: // PTS
          return PTS;
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
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ROAD_LEVEL, new org.apache.thrift.meta_data.FieldMetaData("roadLevel", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PTS, new org.apache.thrift.meta_data.FieldMetaData("pts", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, PointLong.class))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RoadLong.class, metaDataMap);
  }

  public RoadLong() {
  }

  public RoadLong(
    String roadLevel,
    java.util.List<PointLong> pts)
  {
    this();
    this.roadLevel = roadLevel;
    this.pts = pts;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RoadLong(RoadLong other) {
    if (other.isSetRoadLevel()) {
      this.roadLevel = other.roadLevel;
    }
    if (other.isSetPts()) {
      java.util.List<PointLong> __this__pts = new java.util.ArrayList<PointLong>(other.pts.size());
      for (PointLong other_element : other.pts) {
        __this__pts.add(new PointLong(other_element));
      }
      this.pts = __this__pts;
    }
  }

  public RoadLong deepCopy() {
    return new RoadLong(this);
  }

  @Override
  public void clear() {
    this.roadLevel = null;
    this.pts = null;
  }

  @org.apache.thrift.annotation.Nullable
  public String getRoadLevel() {
    return this.roadLevel;
  }

  public RoadLong setRoadLevel(@org.apache.thrift.annotation.Nullable String roadLevel) {
    this.roadLevel = roadLevel;
    return this;
  }

  public void unsetRoadLevel() {
    this.roadLevel = null;
  }

  /** Returns true if field roadLevel is set (has been assigned a value) and false otherwise */
  public boolean isSetRoadLevel() {
    return this.roadLevel != null;
  }

  public void setRoadLevelIsSet(boolean value) {
    if (!value) {
      this.roadLevel = null;
    }
  }

  public int getPtsSize() {
    return (this.pts == null) ? 0 : this.pts.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<PointLong> getPtsIterator() {
    return (this.pts == null) ? null : this.pts.iterator();
  }

  public void addToPts(PointLong elem) {
    if (this.pts == null) {
      this.pts = new java.util.ArrayList<PointLong>();
    }
    this.pts.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<PointLong> getPts() {
    return this.pts;
  }

  public RoadLong setPts(@org.apache.thrift.annotation.Nullable java.util.List<PointLong> pts) {
    this.pts = pts;
    return this;
  }

  public void unsetPts() {
    this.pts = null;
  }

  /** Returns true if field pts is set (has been assigned a value) and false otherwise */
  public boolean isSetPts() {
    return this.pts != null;
  }

  public void setPtsIsSet(boolean value) {
    if (!value) {
      this.pts = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable Object value) {
    switch (field) {
    case ROAD_LEVEL:
      if (value == null) {
        unsetRoadLevel();
      } else {
        setRoadLevel((String)value);
      }
      break;

    case PTS:
      if (value == null) {
        unsetPts();
      } else {
        setPts((java.util.List<PointLong>)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ROAD_LEVEL:
      return getRoadLevel();

    case PTS:
      return getPts();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ROAD_LEVEL:
      return isSetRoadLevel();
    case PTS:
      return isSetPts();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof RoadLong)
      return this.equals((RoadLong)that);
    return false;
  }

  public boolean equals(RoadLong that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_roadLevel = true && this.isSetRoadLevel();
    boolean that_present_roadLevel = true && that.isSetRoadLevel();
    if (this_present_roadLevel || that_present_roadLevel) {
      if (!(this_present_roadLevel && that_present_roadLevel))
        return false;
      if (!this.roadLevel.equals(that.roadLevel))
        return false;
    }

    boolean this_present_pts = true && this.isSetPts();
    boolean that_present_pts = true && that.isSetPts();
    if (this_present_pts || that_present_pts) {
      if (!(this_present_pts && that_present_pts))
        return false;
      if (!this.pts.equals(that.pts))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetRoadLevel()) ? 131071 : 524287);
    if (isSetRoadLevel())
      hashCode = hashCode * 8191 + roadLevel.hashCode();

    hashCode = hashCode * 8191 + ((isSetPts()) ? 131071 : 524287);
    if (isSetPts())
      hashCode = hashCode * 8191 + pts.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(RoadLong other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetRoadLevel()).compareTo(other.isSetRoadLevel());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRoadLevel()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.roadLevel, other.roadLevel);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPts()).compareTo(other.isSetPts());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPts()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pts, other.pts);
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
    StringBuilder sb = new StringBuilder("RoadLong(");
    boolean first = true;

    sb.append("roadLevel:");
    if (this.roadLevel == null) {
      sb.append("null");
    } else {
      sb.append(this.roadLevel);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("pts:");
    if (this.pts == null) {
      sb.append("null");
    } else {
      sb.append(this.pts);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (roadLevel == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'roadLevel' was not present! Struct: " + toString());
    }
    if (pts == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'pts' was not present! Struct: " + toString());
    }
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RoadLongStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RoadLongStandardScheme getScheme() {
      return new RoadLongStandardScheme();
    }
  }

  private static class RoadLongStandardScheme extends org.apache.thrift.scheme.StandardScheme<RoadLong> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RoadLong struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ROAD_LEVEL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.roadLevel = iprot.readString();
              struct.setRoadLevelIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PTS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list8 = iprot.readListBegin();
                struct.pts = new java.util.ArrayList<PointLong>(_list8.size);
                @org.apache.thrift.annotation.Nullable PointLong _elem9;
                for (int _i10 = 0; _i10 < _list8.size; ++_i10)
                {
                  _elem9 = new PointLong();
                  _elem9.read(iprot);
                  struct.pts.add(_elem9);
                }
                iprot.readListEnd();
              }
              struct.setPtsIsSet(true);
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
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, RoadLong struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.roadLevel != null) {
        oprot.writeFieldBegin(ROAD_LEVEL_FIELD_DESC);
        oprot.writeString(struct.roadLevel);
        oprot.writeFieldEnd();
      }
      if (struct.pts != null) {
        oprot.writeFieldBegin(PTS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.pts.size()));
          for (PointLong _iter11 : struct.pts)
          {
            _iter11.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RoadLongTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RoadLongTupleScheme getScheme() {
      return new RoadLongTupleScheme();
    }
  }

  private static class RoadLongTupleScheme extends org.apache.thrift.scheme.TupleScheme<RoadLong> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RoadLong struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeString(struct.roadLevel);
      {
        oprot.writeI32(struct.pts.size());
        for (PointLong _iter12 : struct.pts)
        {
          _iter12.write(oprot);
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RoadLong struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.roadLevel = iprot.readString();
      struct.setRoadLevelIsSet(true);
      {
        org.apache.thrift.protocol.TList _list13 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
        struct.pts = new java.util.ArrayList<PointLong>(_list13.size);
        @org.apache.thrift.annotation.Nullable PointLong _elem14;
        for (int _i15 = 0; _i15 < _list13.size; ++_i15)
        {
          _elem14 = new PointLong();
          _elem14.read(iprot);
          struct.pts.add(_elem14);
        }
      }
      struct.setPtsIsSet(true);
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}
