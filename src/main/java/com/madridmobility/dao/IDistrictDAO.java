package com.madridmobility.dao;

import java.sql.SQLException;
import java.util.List;

import com.madridmobility.beans.DistrictBean;

public interface IDistrictDAO {

	List<DistrictBean> getDistrictsWithTotalBikes() throws SQLException;

}
