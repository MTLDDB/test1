package testSolr.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 产品实体类
 * @author huilin
 *
 */
@Entity
@Table(name="t_product")
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 品牌id
	 *
	 */
	private String objectid;

	/**
	 * 品牌id
	 * 
	 */
	private String mfgid;
	/**
	 * 品牌名字
	 * 
	 */
	private String mfgname;
	/**
	 *分类名字
	 */
	private String categoryname;
	/**
	 *分类id
	 */
	private String categoryid;
	/**
	 *型号
	 */
	private String mpn;
	/**
	 *产品描述
	 */
	private String descript;
	/**
	 *产品状态
	 */
	//private Productstate productstate;
	/**
	 *产品介绍详细信息
	 */
	private String productintroduction;
	/**
	 *产品宣传词
	 */
	private String publicitywords;
	/**
	 *seo关键字
	 */
	private String seokeyword;

	
	/**
	 *产品是否上架
	 * 0:下架，1:上架
	 */
	private int isshelves;
	/**
	 *产品对应的唯一的编号
	 */
	private int partnum;
	
	/**
	 *预留字段1  更新批次号
	 */
	private String obligate1;
	
	/**
	 *预留字段2
	 */
	private String obligate2;
	
	/**
	 *中文描述，2020-05-15确认，该字段解除中文描述，用产品描述表(t_productintroduction)代替
	 */
	private String obligate3;
	
	/**
	 *预留字段4
	 */
	private String obligate4;


	/**
	 * 来自哪个域名
	 */
	private String domain;
	
	/**
	 * 标准包装数量
	 */
	private int spq;

	/**
	 * 替代料号
	 */
	private String cpn;


	public String getObjectid() {
		return objectid;
	}

	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}

	@Column(length=32)
	public String getMfgid() {
		return mfgid;
	}
	public void setMfgid(String mfgid) {
		this.mfgid = mfgid;
	}
	@Column(length=32)
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	@Column(length=500,nullable=false)
	public String getMpn() {
		return mpn;
	}
	public void setMpn(String mpn) {
		this.mpn = mpn;
	}
	@Column(length=500)
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinColumn(name = "productstate_id", insertable = true,nullable=false)
//	public Productstate getProductstate() {
//		return productstate;
//	}
//	public void setProductstate(Productstate productstate) {
//		this.productstate = productstate;
//	}
	@Column(length=500)
	public String getProductintroduction() {
		return productintroduction;
	}
	public void setProductintroduction(String productintroduction) {
		this.productintroduction = productintroduction;
	}
	@Column(length=500)
	public String getPublicitywords() {
		return publicitywords;
	}
	public void setPublicitywords(String publicitywords) {
		this.publicitywords = publicitywords;
	}
	@Column(length=500)
	public String getSeokeyword() {
		return seokeyword;
	}
	public void setSeokeyword(String seokeyword) {
		this.seokeyword = seokeyword;
	}
	@Column(nullable=false)
	public int getIsshelves() {
		return isshelves;
	}
	public void setIsshelves(int isshelves) {
		this.isshelves = isshelves;
	}
    @Column(nullable=false, unique=true )
    public int getPartnum() {
        return partnum;
    }
    public void setPartnum(int partnum) {
        this.partnum = partnum;
    }
	public String getObligate1() {
		return obligate1;
	}
	public void setObligate1(String obligate1) {
		this.obligate1 = obligate1;
	}
	public String getObligate2() {
		return obligate2;
	}
	public void setObligate2(String obligate2) {
		this.obligate2 = obligate2;
	}
	public String getObligate3() {
		return obligate3;
	}
	public void setObligate3(String obligate3) {
		this.obligate3 = obligate3;
	}
	public String getObligate4() {
		return obligate4;
	}
	public void setObligate4(String obligate4) {
		this.obligate4 = obligate4;
	}
	public String getMfgname() {
		return mfgname;
	}
	public void setMfgname(String mfgname) {
		this.mfgname = mfgname;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public int getSpq() {
		return spq;
	}
	public void setSpq(int spq) {
		this.spq = spq;
	}
	@Override
	public String toString() {
		return "Product [mfgid=" + mfgid + ", mfgname=" + mfgname
				+ ", categoryname=" + categoryname + ", categoryid="
				+ categoryid + ", mpn=" + mpn + ", descript=" + descript
				+ ", productstate="
				//+ productstate.getState()
				+ ", productintroduction="
				+ productintroduction + ", publicitywords=" + publicitywords
				+ ", seokeyword=" + seokeyword + ", isshelves=" + isshelves
				+ ", partnum=" + partnum + ", obligate1=" + obligate1
				+ ", obligate2=" + obligate2 + ", obligate3=" + obligate3
				+ ", obligate4=" + obligate4 + ", domain=" + domain + ", spq="
				+ spq + "]";
	}

	public String getCpn() {
		return cpn;
	}

	public void setCpn(String cpn) {
		this.cpn = cpn;
	}
}
