package pandas;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;

public class DataframeTest {

	private Dataframe constructBaseDF_FromVoid() {
		try {
			Dataframe dt = new Dataframe();
			ArrayList<Element> listOfElementLabel = new ArrayList<>();
			listOfElementLabel.add(new Element("Number"));
			listOfElementLabel.add(new Element("Name"));
			listOfElementLabel.add(new Element("Date"));
			Line lLabel = new Line(0, listOfElementLabel);
			dt.changeLabelLine(lLabel);
			ArrayList<Element> listOfElement = new ArrayList<>();
			listOfElement.add(new Element("1"));
			listOfElement.add(new Element("Marion"));
			listOfElement.add(new Element("1995"));
			Line l = new Line(0, listOfElement);
			ArrayList<Element> listOfElement2 = new ArrayList<>();
			listOfElement2.add(new Element("10"));
			listOfElement2.add(new Element("Bruno"));
			listOfElement2.add(new Element("1870"));
			Line l2 = new Line(1, listOfElement2);
			ArrayList<Element> listOfElement3 = new ArrayList<>();
			listOfElement3.add(new Element("5"));
			listOfElement3.add(new Element("Audrey"));
			listOfElement3.add(new Element("2010"));
			Line l3 = new Line(2, listOfElement3);
			dt.addLine(l);
			dt.addLine(l2);
			dt.addLine(l3);

			return dt;
		} catch (ExceptionSizeNotEqual e) {
			return null;
		}

	}

	private Dataframe constructBaseDF_FromCSV() {
		Dataframe dt = new Dataframe("Pandas/src/main/resources/fichier_test.csv");

		return dt;
	}

	@Test
	public void testConstructorEmpty() {
		Dataframe dt = constructBaseDF_FromVoid();

		assertNotNull(dt);
	}

	@Test()
	public void testGetLabelSucceed() throws ExceptionSizeNotEqual {

		Dataframe dt = new Dataframe();

		ArrayList<Element> listOfElementLabel = new ArrayList<>();
		listOfElementLabel.add(new Element("Sexe"));
		listOfElementLabel.add(new Element("Name"));
		listOfElementLabel.add(new Element("Année"));
		Line lLabel = new Line(0, listOfElementLabel);
		dt.changeLabelLine(lLabel);

		assertEquals(lLabel.toString(), dt.getLabels().toString());
	}

	@Test(expected = ExceptionOperationOnEmptyTable.class)
	public void testSumEmptyTable() throws ExceptionSizeNotEqual, ExceptionWrongColumnType, ExceptionWrongIndex, ExceptionOperationOnEmptyTable {

		Dataframe dt = new Dataframe();

		ArrayList<Element> listOfElementLabel = new ArrayList<>();
		listOfElementLabel.add(new Element("Sexe"));
		listOfElementLabel.add(new Element("Name"));
		listOfElementLabel.add(new Element("Année"));
		Line lLabel = new Line(0, listOfElementLabel);
		dt.changeLabelLine(lLabel);

		dt.sumOfColumn(1);
	}

	@Test(expected = ExceptionOperationOnEmptyTable.class)
	public void testOrderEmptyTable() throws ExceptionSizeNotEqual, ExceptionWrongIndex, ExceptionOperationOnEmptyTable {

		Dataframe dt = new Dataframe();

		ArrayList<Element> listOfElementLabel = new ArrayList<>();
		listOfElementLabel.add(new Element("Sexe"));
		listOfElementLabel.add(new Element("Name"));
		listOfElementLabel.add(new Element("Année"));
		Line lLabel = new Line(0, listOfElementLabel);
		dt.changeLabelLine(lLabel);

		ArrayList<Integer> listOfInt = new ArrayList<>();
		listOfInt.add(1);
		listOfInt.add(2);

		dt.orderBy(listOfInt);
	}

	@Test(expected = ExceptionSizeNotEqual.class)
	public void testChangeLabelLineFailed() throws ExceptionSizeNotEqual {

		Dataframe dt = new Dataframe();

		ArrayList<Element> listOfElementLabel = new ArrayList<>();
		listOfElementLabel.add(new Element("Sexe"));
		listOfElementLabel.add(new Element("Name"));
		listOfElementLabel.add(new Element("Année"));
		Line lLabel = new Line(0, listOfElementLabel);
		dt.changeLabelLine(lLabel);


		ArrayList<Element> listOfElement = new ArrayList<>();
		listOfElement.add(new Element("1"));
		listOfElement.add(new Element("Marion"));
		listOfElement.add(new Element("1995"));
		Line l = new Line(0, listOfElement);
		dt.addLine(l);

		listOfElementLabel = new ArrayList<>();
		listOfElementLabel.add(new Element("Sexe"));
		listOfElementLabel.add(new Element("Name"));
		lLabel = new Line(0, listOfElementLabel);
		dt.changeLabelLine(lLabel);
	}

	@Test()
	public void testChangeLabelLineSucceed() throws ExceptionSizeNotEqual {

		Dataframe dt = new Dataframe();

		ArrayList<Element> listOfElementLabel = new ArrayList<>();
		listOfElementLabel.add(new Element("Sexe"));
		listOfElementLabel.add(new Element("Name"));
		listOfElementLabel.add(new Element("Année"));
		Line lLabel = new Line(0, listOfElementLabel);
		dt.changeLabelLine(lLabel);


		ArrayList<Element> listOfElement = new ArrayList<>();
		listOfElement.add(new Element("1"));
		listOfElement.add(new Element("Marion"));
		listOfElement.add(new Element("1995"));
		Line l = new Line(0, listOfElement);
		dt.addLine(l);

		listOfElementLabel = new ArrayList<>();
		listOfElementLabel.add(new Element("Name"));
		listOfElementLabel.add(new Element("Sexe"));
		listOfElementLabel.add(new Element("Année"));
		lLabel = new Line(0, listOfElementLabel);
		dt.changeLabelLine(lLabel);

		assertEquals(lLabel.toString(), dt.getLabels().toString());
	}

	@Test(expected = ExceptionSizeNotEqual.class)
	public void testAddLineFailed() throws ExceptionSizeNotEqual {

		Dataframe dt = new Dataframe();

		ArrayList<Element> listOfElementLabel = new ArrayList<>();
		listOfElementLabel.add(new Element("Sexe"));
		listOfElementLabel.add(new Element("Name"));
		Line lLabel = new Line(0, listOfElementLabel);
		dt.changeLabelLine(lLabel);


		ArrayList<Element> listOfElement = new ArrayList<>();
		listOfElement.add(new Element("1"));
		listOfElement.add(new Element("Marion"));
		listOfElement.add(new Element("1995"));
		Line l = new Line(0, listOfElement);
		dt.addLine(l);
	}

	@Test
	public void testConstructorEmpty_1() {
		Dataframe dt = constructBaseDF_FromCSV();

		assertNotNull(dt);
	}
	
	/**
	 * Test on get sub of data frame with sequence of line
	 * */
	@Test
	public void testGetSubDataFrameFromLines() {
		Dataframe dt = constructBaseDF_FromVoid();

		ArrayList<Integer> column = new ArrayList<>();
		column.add(0);
		column.add(1);
		column.add(2);

		Dataframe dtSub = dt.getSubDataFrameFromLines(column);
		assertEquals(dt.toString(), dtSub.toString());
	}

	/**
	 * Test on get sub of data frame with sequence of column number
	 * */
	@Test
	public void testGetSubDataFrameFromColumnsNumber() throws ExceptionWrongIndex {
		Dataframe dt = constructBaseDF_FromVoid();

		ArrayList<Integer> column = new ArrayList<>();
		column.add(0);
		column.add(1);
		column.add(2);

		Dataframe dtSub = dt.getSubDataFrameFromColumnsNumber(column);
		assertEquals(dt.toString(), dtSub.toString());
	}

	/**
	 * Test on get sub of data frame with sequence of column label
	 * */
	@Test
	public void testGetSubDataFrameFromColumnsLabel() throws ExceptionWrongIndex {
		Dataframe dt = constructBaseDF_FromVoid();

		ArrayList<String> nameOfColumn = new ArrayList<>();
		nameOfColumn.add("Number");
		nameOfColumn.add("Name");
		nameOfColumn.add("Date");
		Dataframe dtSub = dt.getSubDataFrameFromColumnsLabel(nameOfColumn);

		assertEquals(dt.toString(), dtSub.toString());
	}

	/**
	 * Tests for maximum value function
	 * */
	@Test
	public void testMaxElementByColumnIndex() throws ExceptionOperationOnEmptyTable, ExceptionWrongIndex {
		Dataframe dt = constructBaseDF_FromVoid();

		Element eNumber = dt.maxValueByColumnIndex(0);
		assertEquals("10", eNumber.getData());
		Element eName = dt.maxValueByColumnIndex(1);
		assertEquals("Marion", eName.getData());
	}

	@Test(expected = ExceptionWrongIndex.class)
	public void testMaxElementByColumnIndexNonExistingColumn() throws ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = constructBaseDF_FromVoid();

		Element eNullSup = dt.maxValueByColumnIndex(-1);
	}

	@Test(expected = ExceptionOperationOnEmptyTable.class)
	public void testMaxElementByColumnIndexEmptyTable() throws ExceptionSizeNotEqual, ExceptionOperationOnEmptyTable, ExceptionWrongIndex {
		Dataframe dt = new Dataframe();

		ArrayList<Element> listOfElementLabel = new ArrayList<>();
		listOfElementLabel.add(new Element("Number"));
		listOfElementLabel.add(new Element("Name"));
		listOfElementLabel.add(new Element("Date"));
		Line lLabel = new Line(0, listOfElementLabel);
		dt.changeLabelLine(lLabel);

		Element eNullSup = dt.maxValueByColumnIndex(1);
	}

	/**
	 * Tests for maximum value function by label
	 * */
	@Test
	public void testMaxElementByColumnLabel() throws ExceptionNoLabel, ExceptionUnknownColumn, ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = constructBaseDF_FromVoid();

		Element eName = dt.maxValueByLabel("Name");
		assertEquals("Marion", eName.getData());
		Element eDate = dt.maxValueByLabel("Date");
		assertEquals("2010", eDate.getData());
	}

	@Test(expected = ExceptionNoLabel.class)
	public void testMaxElementByColumnLabelNonExistingColumn() throws ExceptionNoLabel, ExceptionUnknownColumn, ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = new Dataframe();

		Element eNullSup = dt.maxValueByLabel("faux");
	}

	/**
	 * Tests for minimum value function
	 * */
	@Test
	public void testMinElementByColumnIndex() throws ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = constructBaseDF_FromVoid();

		Element eNumber = dt.minValueByColumnIndex(0);
		assertEquals("1", eNumber.getData());
		Element eName = dt.minValueByColumnIndex(1);
		assertEquals("Bruno", eName.getData()); // Bruno est plus court qu'Audrey
	}

	@Test(expected = ExceptionWrongIndex.class)
	public void testMinElementByColumnIndexNonExistingColumn() throws ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = constructBaseDF_FromVoid();

		Element eNullSup = dt.minValueByColumnIndex(-1);
	}

	/**
	 * Tests for minimum value function by label
	 * */
	@Test
	public void testMinElementByColumnLabel() throws ExceptionNoLabel, ExceptionUnknownColumn, ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = constructBaseDF_FromVoid();

		Element eNumber = dt.minValueByLabel("Number");
		assertEquals("1", eNumber.getData());
		Element eName = dt.minValueByLabel("Name");
		assertEquals("Bruno", eName.getData());
	}

	@Test(expected = ExceptionNoLabel.class)
	public void testMinElementByColumnLabelNonExistingColumn() throws ExceptionNoLabel, ExceptionUnknownColumn, ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = new Dataframe();

		Element eNullSup = dt.minValueByLabel("faux");
	}

	/***
	 * Tests for mean function
	 * */
	@Test
	public void testMeanValueByIndex() throws ExceptionWrongIndex, ExceptionOperationOnEmptyTable, ExceptionWrongColumnType {
		Dataframe dt = constructBaseDF_FromVoid();

		Element mean = dt.meanValueByIndex(0);
		assertEquals((16.0/3.0), mean.getData());
	}

	@Test(expected = ExceptionWrongIndex.class)
	public void testMeanValueByInvalidIndex() throws ExceptionWrongIndex, ExceptionOperationOnEmptyTable, ExceptionWrongColumnType {
		Dataframe dt = constructBaseDF_FromVoid();

		Element eNullInf = dt.meanValueByIndex(-1);
	}

	/***
	 * Tests for mean function with label
	 * */
	@Test
	public void testMeanValueByLabel() throws ExceptionNoLabel, ExceptionUnknownColumn, ExceptionWrongColumnType, ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = constructBaseDF_FromVoid();

		Element mean = dt.meanValueByLabel("Number");
		assertEquals((16.0/3.0), mean.getData());
	}

	@Test(expected = ExceptionUnknownColumn.class)
	public void testMeanValueByInvalidLabel() throws ExceptionNoLabel, ExceptionUnknownColumn, ExceptionWrongColumnType, ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = constructBaseDF_FromVoid();

		Element mean = dt.meanValueByLabel("Faux");
	}

	/***
	 * Tests for select line where
	 * */
	@Test
	public void testSelectLineWhere() throws ExceptionSizeNotEqual, ExceptionUnknownColumn, ExceptionWrongIndex {
		Dataframe dt = constructBaseDF_FromVoid();

		ArrayList<Integer> column = new ArrayList<>();
		column.add(1); //"Name"
		ArrayList<String> columnValue = new ArrayList<>();
		columnValue.add("Bruno");
		Dataframe selectDataframe = dt.selectLineWhere(column, columnValue);
		boolean correctValue = true;
		if(selectDataframe.getLines().size() != 1
			|| selectDataframe.getLines().get(0).getElementByIndex(1).getData() != "Bruno"){
			correctValue = false;
		}
		assertTrue(correctValue);
	}

	@Test
	public void testSelectLineWhereValueNotIn() throws ExceptionSizeNotEqual, ExceptionWrongIndex, ExceptionUnknownColumn {
		Dataframe dt = constructBaseDF_FromVoid();

		ArrayList<Integer> column = new ArrayList<>();
		column.add(0);
		ArrayList<String> columnValue = new ArrayList<>();
		columnValue.add("Bruno");
		Dataframe selectDataframe = dt.selectLineWhere(column, columnValue);
		assertTrue(selectDataframe.getLines().size() == 0);
	}

	@Test(expected = ExceptionWrongIndex.class)
	public void testSelectLineWhereValueColumnNotIn() throws ExceptionSizeNotEqual, ExceptionWrongIndex, ExceptionUnknownColumn {
		Dataframe dt = constructBaseDF_FromVoid();

		ArrayList<Integer> column = new ArrayList<>();
		column.add(-1);
		ArrayList<String> columnValue = new ArrayList<>();
		columnValue.add("Bruno");
		Dataframe selectDataframe = dt.selectLineWhere(column, columnValue);
	}

	/***
	 * Tests for select line where with label
	 * */
	@Test
	public void testSelectLineWhereWithLabel() throws ExceptionSizeNotEqual, ExceptionUnknownColumn, ExceptionWrongIndex {
		Dataframe dt = constructBaseDF_FromVoid();

		ArrayList<String> column = new ArrayList<>();
		column.add("Name");
		ArrayList<String> columnValue = new ArrayList<>();
		columnValue.add("Bruno");
		Dataframe selectDataframe = dt.selectLineWhereByLabel(column, columnValue);
		boolean correctValue = true;
		if(selectDataframe.getLines().size() != 1
				|| selectDataframe.getLines().get(0).getElementByIndex(1).getData() != "Bruno"){
			correctValue = false;
		}
		assertTrue(correctValue);
	}

	@Test(expected = ExceptionUnknownColumn.class)
	public void testSelectLineWhereWithLabelNotIn() throws ExceptionSizeNotEqual, ExceptionWrongIndex, ExceptionUnknownColumn {
		Dataframe dt = constructBaseDF_FromVoid();

		ArrayList<String> column = new ArrayList<>();
		column.add("Faux");
		ArrayList<String> columnValue = new ArrayList<>();
		columnValue.add("Bruno");
		Dataframe selectDataframe = dt.selectLineWhereByLabel(column, columnValue);
	}

	/***
	 * Tests for sum function
	 * */
	@Test
	public void testSumOfColumn() throws ExceptionWrongColumnType, ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = constructBaseDF_FromVoid();

		Double result = dt.sumOfColumn(0);
		assertEquals(16.0, result, 0.001);
	}

	@Test(expected = ExceptionWrongColumnType.class)
	public void testSumOfColumnInvalidColumn() throws ExceptionWrongColumnType, ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = constructBaseDF_FromVoid();

		Double result = dt.sumOfColumn(1);
	}

	@Test(expected = ExceptionWrongIndex.class)
	public void testSumOfColumnWrongIndex() throws ExceptionWrongColumnType, ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = constructBaseDF_FromVoid();

		Double result = dt.sumOfColumn(-1);
	}

	/***
	 * Tests for sum function by label
	 * */
	@Test
	public void testSumOfColumnByLabel() throws ExceptionWrongColumnType, ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = constructBaseDF_FromVoid();

		Double result = dt.sumOfColumnByLabel("Number");
		assertEquals(16.0, result, 0.001);
	}

	@Test(expected = ExceptionWrongColumnType.class)
	public void testSumOfColumnByLabelInvalidColumn() throws ExceptionWrongColumnType, ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = constructBaseDF_FromVoid();

		Double result = dt.sumOfColumnByLabel("Name");
	}

	@Test(expected = ExceptionWrongIndex.class)
	public void testSumOfColumnByLabelWrongLabel() throws ExceptionWrongColumnType, ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = constructBaseDF_FromVoid();

		Double result = dt.sumOfColumnByLabel("Faux");
	}

	/***
	 * Tests for order by function
	 * */
	@Test
	public void testOrderByStringColumn() throws ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = constructBaseDF_FromVoid();
		dt.orderBy(1);

		boolean goodSort = true;
		if(dt.getLines().size() != 3){
			goodSort = false;
		}
		//Check for the first line
		if(dt.getLines().get(0).getElementByIndex(0).compareTo(new Element<String>("10")) != 0
				|| dt.getLines().get(0).getElementByIndex(1).compareTo(new Element<String>("Bruno")) != 0
				|| dt.getLines().get(0).getElementByIndex(2).compareTo(new Element<String>("1870")) != 0){
			goodSort = false;
		}
		//Check for the second line
		if(dt.getLines().get(1).getElementByIndex(0).compareTo(new Element<String>("5")) != 0
				|| dt.getLines().get(1).getElementByIndex(1).compareTo(new Element<String>("Audrey")) != 0
				|| dt.getLines().get(1).getElementByIndex(2).compareTo(new Element<String>("2010")) != 0){
			goodSort = false;
		}
		//Check for the third line
		if(dt.getLines().get(2).getElementByIndex(0).compareTo(new Element<String>("1")) != 0
				|| dt.getLines().get(2).getElementByIndex(1).compareTo(new Element<String>("Marion")) != 0
				|| dt.getLines().get(2).getElementByIndex(2).compareTo(new Element<String>("1995")) != 0) {
			goodSort = false;
		}
		assertTrue(goodSort);
	}

	@Test
	public void testOrderByIntColumn() throws ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = constructBaseDF_FromVoid();
		dt.orderBy(0);

		boolean goodSort = true;
		if(dt.getLines().size() != 3){
			goodSort = false;
		}
		//Check for the first line
		if(dt.getLines().get(0).getElementByIndex(0).compareTo(new Element<String>("1")) != 0
				|| dt.getLines().get(0).getElementByIndex(1).compareTo(new Element<String>("Marion")) != 0
				|| dt.getLines().get(0).getElementByIndex(2).compareTo(new Element<String>("1995")) != 0){
			goodSort = false;
		}
		//Check for the second line
		if(dt.getLines().get(1).getElementByIndex(0).compareTo(new Element<String>("5")) != 0
				|| dt.getLines().get(1).getElementByIndex(1).compareTo(new Element<String>("Audrey")) != 0
				|| dt.getLines().get(1).getElementByIndex(2).compareTo(new Element<String>("2010")) != 0){
			goodSort = false;
		}
		//Check for the third line
		if(dt.getLines().get(2).getElementByIndex(0).compareTo(new Element<String>("10")) != 0
				|| dt.getLines().get(2).getElementByIndex(1).compareTo(new Element<String>("Bruno")) != 0
				|| dt.getLines().get(2).getElementByIndex(2).compareTo(new Element<String>("1870")) != 0){
			goodSort = false;
		}
		assertTrue(goodSort);
	}

	@Test(expected = ExceptionWrongIndex.class)
	public void testOrderByIntWrongColumn() throws ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = constructBaseDF_FromVoid();

		dt.orderBy(-1);
	}

	/***
	 * Tests for order by function
	 * */
	@Test
	public void testOrderByStringLabel() throws ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = constructBaseDF_FromVoid();

		dt.orderByLabel("Name");

		boolean goodSort = true;
		if(dt.getLines().size() != 3){
			goodSort = false;
		}
		//Check for the first line
		if(dt.getLines().get(0).getElementByIndex(0).compareTo(new Element<String>("10")) != 0
				|| dt.getLines().get(0).getElementByIndex(1).compareTo(new Element<String>("Bruno")) != 0
				|| dt.getLines().get(0).getElementByIndex(2).compareTo(new Element<String>("1870")) != 0){
			goodSort = false;
		}
		//Check for the second line
		if(dt.getLines().get(1).getElementByIndex(0).compareTo(new Element<String>("5")) != 0
				|| dt.getLines().get(1).getElementByIndex(1).compareTo(new Element<String>("Audrey")) != 0
				|| dt.getLines().get(1).getElementByIndex(2).compareTo(new Element<String>("2010")) != 0){
			goodSort = false;
		}
		//Check for the third line
		if(dt.getLines().get(2).getElementByIndex(0).compareTo(new Element<String>("1")) != 0
				|| dt.getLines().get(2).getElementByIndex(1).compareTo(new Element<String>("Marion")) != 0
				|| dt.getLines().get(2).getElementByIndex(2).compareTo(new Element<String>("1995")) != 0) {
			goodSort = false;
		}
		assertTrue(goodSort);
	}

	@Test
	public void testOrderByIntLabel() throws ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = constructBaseDF_FromVoid();
		dt.orderByLabel("Number");

		boolean goodSort = true;
		if(dt.getLines().size() != 3){
			goodSort = false;
		}
		//Check for the first line
		if(dt.getLines().get(0).getElementByIndex(0).compareTo(new Element<String>("1")) != 0
				|| dt.getLines().get(0).getElementByIndex(1).compareTo(new Element<String>("Marion")) != 0
				|| dt.getLines().get(0).getElementByIndex(2).compareTo(new Element<String>("1995")) != 0){
			goodSort = false;
		}
		//Check for the second line
		if(dt.getLines().get(1).getElementByIndex(0).compareTo(new Element<String>("5")) != 0
				|| dt.getLines().get(1).getElementByIndex(1).compareTo(new Element<String>("Audrey")) != 0
				|| dt.getLines().get(1).getElementByIndex(2).compareTo(new Element<String>("2010")) != 0){
			goodSort = false;
		}
		//Check for the third line
		if(dt.getLines().get(2).getElementByIndex(0).compareTo(new Element<String>("10")) != 0
				|| dt.getLines().get(2).getElementByIndex(1).compareTo(new Element<String>("Bruno")) != 0
				|| dt.getLines().get(2).getElementByIndex(2).compareTo(new Element<String>("1870")) != 0){
			goodSort = false;
		}
		assertTrue(goodSort);
	}

	@Test(expected = ExceptionWrongIndex.class)
	public void testOrderByIntNotInLabel() throws ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = constructBaseDF_FromVoid();

		dt.orderByLabel("Faux");
	}

	/***
	 * Tests for order by with list function
	 * */
	@Test
	public void testOrderByStringListIndex() throws ExceptionSizeNotEqual, ExceptionOperationOnEmptyTable, ExceptionWrongIndex {
		Dataframe dt = new Dataframe();

		ArrayList<Element> listOfElementLabel = new ArrayList<>();
		listOfElementLabel.add(new Element("Sexe"));
		listOfElementLabel.add(new Element("Name"));
		listOfElementLabel.add(new Element("Date"));
		Line lLabel = new Line(0, listOfElementLabel);
		dt.changeLabelLine(lLabel);

		ArrayList<Element> listOfElement = new ArrayList<>();
		listOfElement.add(new Element("1"));
		listOfElement.add(new Element("Marion"));
		listOfElement.add(new Element("1995"));
		Line l = new Line(0, listOfElement);
		ArrayList<Element> listOfElement2 = new ArrayList<>();
		listOfElement2.add(new Element("1"));
		listOfElement2.add(new Element("Bruno"));
		listOfElement2.add(new Element("1870"));
		Line l2 = new Line(1, listOfElement2);
		ArrayList<Element> listOfElement3 = new ArrayList<>();
		listOfElement3.add(new Element("1"));
		listOfElement3.add(new Element("Audrey"));
		listOfElement3.add(new Element("2010"));
		Line l3 = new Line(2, listOfElement3);
		dt.addLine(l);
		dt.addLine(l2);
		dt.addLine(l3);

		ArrayList<Integer> listOfColumn = new ArrayList<>();
		listOfColumn.add(0);
		listOfColumn.add(1);

		dt.orderBy(listOfColumn);

		boolean goodSort = true;
		if(dt.getLines().size() != 3){
			goodSort = false;
		}
		//Check for the second line
		if(dt.getLines().get(0).getElementByIndex(0).compareTo(new Element<String>("1")) != 0
				|| dt.getLines().get(0).getElementByIndex(1).compareTo(new Element<String>("Bruno")) != 0
				|| dt.getLines().get(0).getElementByIndex(2).compareTo(new Element<String>("1870")) != 0){
			goodSort = false;
		}
		//Check for the first line
		if(dt.getLines().get(1).getElementByIndex(0).compareTo(new Element<String>("1")) != 0
				|| dt.getLines().get(1).getElementByIndex(1).compareTo(new Element<String>("Audrey")) != 0
				|| dt.getLines().get(1).getElementByIndex(2).compareTo(new Element<String>("2010")) != 0){
			goodSort = false;
		}
		//Check for the third line
		if(dt.getLines().get(2).getElementByIndex(0).compareTo(new Element<String>("1")) != 0
				|| dt.getLines().get(2).getElementByIndex(1).compareTo(new Element<String>("Marion")) != 0
				|| dt.getLines().get(2).getElementByIndex(2).compareTo(new Element<String>("1995")) != 0){
			goodSort = false;
		}
		assertTrue(goodSort);
	}

	@Test
	public void testOrderByIntListIndex() throws ExceptionSizeNotEqual, ExceptionOperationOnEmptyTable, ExceptionWrongIndex {
		Dataframe dt = new Dataframe();

		ArrayList<Element> listOfElementLabel = new ArrayList<>();
		listOfElementLabel.add(new Element("Sexe"));
		listOfElementLabel.add(new Element("Name"));
		listOfElementLabel.add(new Element("Date"));
		Line lLabel = new Line(0, listOfElementLabel);
		dt.changeLabelLine(lLabel);

		ArrayList<Element> listOfElement = new ArrayList<>();
		listOfElement.add(new Element("1"));
		listOfElement.add(new Element("Marion"));
		listOfElement.add(new Element("1995"));
		Line l = new Line(0, listOfElement);
		ArrayList<Element> listOfElement2 = new ArrayList<>();
		listOfElement2.add(new Element("2"));
		listOfElement2.add(new Element("Audrey"));
		listOfElement2.add(new Element("1870"));
		Line l2 = new Line(1, listOfElement2);
		ArrayList<Element> listOfElement3 = new ArrayList<>();
		listOfElement3.add(new Element("3"));
		listOfElement3.add(new Element("Audrey"));
		listOfElement3.add(new Element("2010"));
		Line l3 = new Line(2, listOfElement3);
		dt.addLine(l);
		dt.addLine(l2);
		dt.addLine(l3);

		ArrayList<Integer> listOfColumn = new ArrayList<>();
		listOfColumn.add(1);
		listOfColumn.add(0);

		dt.orderBy(listOfColumn);
		boolean goodSort = true;
		if(dt.getLines().size() != 3){
			goodSort = false;
		}
		//Check for the first line
		if(dt.getLines().get(0).getElementByIndex(0).compareTo(new Element<String>("2")) != 0
				|| dt.getLines().get(0).getElementByIndex(1).compareTo(new Element<String>("Audrey")) != 0
				|| dt.getLines().get(0).getElementByIndex(2).compareTo(new Element<String>("1870")) != 0){
			goodSort = false;
		}
		//Check for the second line
		if(dt.getLines().get(1).getElementByIndex(0).compareTo(new Element<String>("3")) != 0
				|| dt.getLines().get(1).getElementByIndex(1).compareTo(new Element<String>("Audrey")) != 0
				|| dt.getLines().get(1).getElementByIndex(2).compareTo(new Element<String>("2010")) != 0){
			goodSort = false;
		}
		//Check for the third line
		if(dt.getLines().get(2).getElementByIndex(0).compareTo(new Element<String>("1")) != 0
				|| dt.getLines().get(2).getElementByIndex(1).compareTo(new Element<String>("Marion")) != 0
				|| dt.getLines().get(2).getElementByIndex(2).compareTo(new Element<String>("1995")) != 0){
			goodSort = false;
		}
		assertTrue(goodSort);
	}

	@Test
	public void testOrderByIntListIndexExceptionNotExcepted() throws ExceptionSizeNotEqual, ExceptionOperationOnEmptyTable, ExceptionWrongIndex {
		Dataframe dt = new Dataframe();

		ArrayList<Element> listOfElementLabel = new ArrayList<>();
		listOfElementLabel.add(new Element("Sexe"));
		listOfElementLabel.add(new Element("Name"));
		listOfElementLabel.add(new Element("Date"));
		Line lLabel = new Line(0, listOfElementLabel);
		dt.changeLabelLine(lLabel);

		ArrayList<Element> listOfElement = new ArrayList<>();
		listOfElement.add(new Element("2"));
		listOfElement.add(new Element("Audrey"));
		listOfElement.add(new Element("1995"));
		Line l = new Line(0, listOfElement);
		ArrayList<Element> listOfElement2 = new ArrayList<>();
		listOfElement2.add(new Element("3"));
		listOfElement2.add(new Element("Audrey"));
		listOfElement2.add(new Element("1870"));
		Line l2 = new Line(1, listOfElement2);
		dt.addLine(l);
		dt.addLine(l2);

		ArrayList<Integer> listOfColumn = new ArrayList<>();
		listOfColumn.add(1);
		listOfColumn.add(2);

		dt.orderBy(listOfColumn);

		boolean goodSort = true;
		if(dt.getLines().size() != 2){
			goodSort = false;
		}
		//Check for the first line
		if(dt.getLines().get(0).getElementByIndex(0).compareTo(new Element<String>("3")) != 0
				|| dt.getLines().get(0).getElementByIndex(1).compareTo(new Element<String>("Audrey")) != 0
				|| dt.getLines().get(0).getElementByIndex(2).compareTo(new Element<String>("1870")) != 0){
			goodSort = false;
		}
		//Check for the second line
		if(dt.getLines().get(1).getElementByIndex(0).compareTo(new Element<String>("2")) != 0
				|| dt.getLines().get(1).getElementByIndex(1).compareTo(new Element<String>("Audrey")) != 0
				|| dt.getLines().get(1).getElementByIndex(2).compareTo(new Element<String>("1995")) != 0){
			goodSort = false;
		}
		assertTrue(goodSort);
	}

	@Test
	public void testOrderByListIndexSameValue() throws ExceptionSizeNotEqual, ExceptionOperationOnEmptyTable, ExceptionWrongIndex {
		Dataframe dt = new Dataframe();

		ArrayList<Element> listOfElementLabel = new ArrayList<>();
		listOfElementLabel.add(new Element("Sexe"));
		listOfElementLabel.add(new Element("Name"));
		listOfElementLabel.add(new Element("Date"));
		Line lLabel = new Line(0, listOfElementLabel);
		dt.changeLabelLine(lLabel);

		ArrayList<Element> listOfElement = new ArrayList<>();
		listOfElement.add(new Element("1"));
		listOfElement.add(new Element("Marion"));
		listOfElement.add(new Element("1995"));
		Line l = new Line(0, listOfElement);
		ArrayList<Element> listOfElement2 = new ArrayList<>();
		listOfElement2.add(new Element("2"));
		listOfElement2.add(new Element("Audrey"));
		listOfElement2.add(new Element("1870"));
		Line l2 = new Line(1, listOfElement2);
		ArrayList<Element> listOfElement3 = new ArrayList<>();
		listOfElement3.add(new Element("2"));
		listOfElement3.add(new Element("Audrey"));
		listOfElement3.add(new Element("2010"));
		Line l3 = new Line(2, listOfElement3);
		dt.addLine(l);
		dt.addLine(l2);
		dt.addLine(l3);

		ArrayList<Integer> listOfColumn = new ArrayList<>();
		listOfColumn.add(1);
		listOfColumn.add(0);

		dt.orderBy(listOfColumn);
		boolean goodSort = true;
		if(dt.getLines().size() != 3){
			goodSort = false;
		}
		//Check for the first line
		if(dt.getLines().get(0).getElementByIndex(0).compareTo(new Element<String>("2")) != 0
				|| dt.getLines().get(0).getElementByIndex(1).compareTo(new Element<String>("Audrey")) != 0
				|| dt.getLines().get(0).getElementByIndex(2).compareTo(new Element<String>("1870")) != 0){
			goodSort = false;
		}
		//Check for the second line
		if(dt.getLines().get(1).getElementByIndex(0).compareTo(new Element<String>("2")) != 0
				|| dt.getLines().get(1).getElementByIndex(1).compareTo(new Element<String>("Audrey")) != 0
				|| dt.getLines().get(1).getElementByIndex(2).compareTo(new Element<String>("2010")) != 0){
			goodSort = false;
		}
		//Check for the third line
		if(dt.getLines().get(2).getElementByIndex(0).compareTo(new Element<String>("1")) != 0
				|| dt.getLines().get(2).getElementByIndex(1).compareTo(new Element<String>("Marion")) != 0
				|| dt.getLines().get(2).getElementByIndex(2).compareTo(new Element<String>("1995")) != 0){
			goodSort = false;
		}
		assertTrue(goodSort);
	}

	@Test(expected = ExceptionWrongIndex.class)
	public void testOrderByIntNotInListIndex() throws ExceptionSizeNotEqual, ExceptionOperationOnEmptyTable, ExceptionWrongIndex {
		Dataframe dt = new Dataframe();

		ArrayList<Element> listOfElementLabel = new ArrayList<>();
		listOfElementLabel.add(new Element("Sexe"));
		listOfElementLabel.add(new Element("Name"));
		listOfElementLabel.add(new Element("Date"));
		Line lLabel = new Line(0, listOfElementLabel);
		dt.changeLabelLine(lLabel);

		ArrayList<Element> listOfElement = new ArrayList<>();
		listOfElement.add(new Element("1"));
		listOfElement.add(new Element("Marion"));
		listOfElement.add(new Element("1995"));
		Line l = new Line(0, listOfElement);
		ArrayList<Element> listOfElement2 = new ArrayList<>();
		listOfElement2.add(new Element("2"));
		listOfElement2.add(new Element("Bruno"));
		listOfElement2.add(new Element("1870"));
		Line l2 = new Line(1, listOfElement2);
		ArrayList<Element> listOfElement3 = new ArrayList<>();
		listOfElement3.add(new Element("3"));
		listOfElement3.add(new Element("Audrey"));
		listOfElement3.add(new Element("2010"));
		Line l3 = new Line(2, listOfElement3);
		dt.addLine(l);
		dt.addLine(l2);
		dt.addLine(l3);

		ArrayList<Integer> listOfColumn = new ArrayList<>();
		listOfColumn.add(5);
		listOfColumn.add(-1);

		dt.orderBy(listOfColumn);

		boolean goodSort = true;
		if(dt.getLines().size() != 3){
			goodSort = false;
		}
		//Check for the first line
		if(dt.getLines().get(0).getElementByIndex(0).compareTo(new Element<String>("1")) != 0
				|| dt.getLines().get(0).getElementByIndex(1).compareTo(new Element<String>("Marion")) != 0
				|| dt.getLines().get(0).getElementByIndex(2).compareTo(new Element<String>("1995")) != 0){
			goodSort = false;
		}
		//Check for the second line
		if(dt.getLines().get(1).getElementByIndex(0).compareTo(new Element<String>("2")) != 0
				|| dt.getLines().get(1).getElementByIndex(1).compareTo(new Element<String>("Bruno")) != 0
				|| dt.getLines().get(1).getElementByIndex(2).compareTo(new Element<String>("1870")) != 0){
			goodSort = false;
		}
		//Check for the third line
		if(dt.getLines().get(2).getElementByIndex(0).compareTo(new Element<String>("3")) != 0
				|| dt.getLines().get(2).getElementByIndex(1).compareTo(new Element<String>("Audrey")) != 0
				|| dt.getLines().get(2).getElementByIndex(2).compareTo(new Element<String>("2010")) != 0){
			goodSort = false;
		}
		assertTrue(goodSort);
	}

	/***
	 * Tests for order by with list function
	 * */
	@Test
	public void testOrderByStringListLabel() throws ExceptionSizeNotEqual, ExceptionOperationOnEmptyTable, ExceptionWrongIndex {
		Dataframe dt = new Dataframe();

		ArrayList<Element> listOfElementLabel = new ArrayList<>();
		listOfElementLabel.add(new Element("Sexe"));
		listOfElementLabel.add(new Element("Name"));
		listOfElementLabel.add(new Element("Date"));
		Line lLabel = new Line(0, listOfElementLabel);
		dt.changeLabelLine(lLabel);

		ArrayList<Element> listOfElement = new ArrayList<>();
		listOfElement.add(new Element("1"));
		listOfElement.add(new Element("Marion"));
		listOfElement.add(new Element("1995"));
		Line l = new Line(0, listOfElement);
		ArrayList<Element> listOfElement2 = new ArrayList<>();
		listOfElement2.add(new Element("1"));
		listOfElement2.add(new Element("Bruno"));
		listOfElement2.add(new Element("1870"));
		Line l2 = new Line(1, listOfElement2);
		ArrayList<Element> listOfElement3 = new ArrayList<>();
		listOfElement3.add(new Element("1"));
		listOfElement3.add(new Element("Audrey"));
		listOfElement3.add(new Element("2010"));
		Line l3 = new Line(2, listOfElement3);
		dt.addLine(l);
		dt.addLine(l2);
		dt.addLine(l3);

		ArrayList<String> listOfColumn = new ArrayList<>();
		listOfColumn.add("Sexe");
		listOfColumn.add("Name");

		dt.orderByLabel(listOfColumn);

		boolean goodSort = true;
		if(dt.getLines().size() != 3){
			goodSort = false;
		}
		//Check for the first line
		if(dt.getLines().get(0).getElementByIndex(0).compareTo(new Element<String>("1")) != 0
				|| dt.getLines().get(0).getElementByIndex(1).compareTo(new Element<String>("Bruno")) != 0
				|| dt.getLines().get(0).getElementByIndex(2).compareTo(new Element<String>("1870")) != 0){
			goodSort = false;
		}
		//Check for the second line
		if(dt.getLines().get(1).getElementByIndex(0).compareTo(new Element<String>("1")) != 0
				|| dt.getLines().get(1).getElementByIndex(1).compareTo(new Element<String>("Audrey")) != 0
				|| dt.getLines().get(1).getElementByIndex(2).compareTo(new Element<String>("2010")) != 0){
			goodSort = false;
		}
		//Check for the third line
		if(dt.getLines().get(2).getElementByIndex(0).compareTo(new Element<String>("1")) != 0
				|| dt.getLines().get(2).getElementByIndex(1).compareTo(new Element<String>("Marion")) != 0
				|| dt.getLines().get(2).getElementByIndex(2).compareTo(new Element<String>("1995")) != 0){
			goodSort = false;
		}
		assertTrue(goodSort);
	}

	@Test
	public void testOrderByIntListLabel() throws ExceptionSizeNotEqual, ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = new Dataframe();

		ArrayList<Element> listOfElementLabel = new ArrayList<>();
		listOfElementLabel.add(new Element("Sexe"));
		listOfElementLabel.add(new Element("Name"));
		listOfElementLabel.add(new Element("Date"));
		Line lLabel = new Line(0, listOfElementLabel);
		dt.changeLabelLine(lLabel);

		ArrayList<Element> listOfElement = new ArrayList<>();
		listOfElement.add(new Element("1"));
		listOfElement.add(new Element("Marion"));
		listOfElement.add(new Element("1995"));
		Line l = new Line(0, listOfElement);
		ArrayList<Element> listOfElement2 = new ArrayList<>();
		listOfElement2.add(new Element("2"));
		listOfElement2.add(new Element("Audrey"));
		listOfElement2.add(new Element("1870"));
		Line l2 = new Line(1, listOfElement2);
		ArrayList<Element> listOfElement3 = new ArrayList<>();
		listOfElement3.add(new Element("3"));
		listOfElement3.add(new Element("Audrey"));
		listOfElement3.add(new Element("2010"));
		Line l3 = new Line(2, listOfElement3);
		dt.addLine(l);
		dt.addLine(l2);
		dt.addLine(l3);

		ArrayList<String> listOfColumn = new ArrayList<>();
		listOfColumn.add("Name");
		listOfColumn.add("Sexe");

		dt.orderByLabel(listOfColumn);

		boolean goodSort = true;
		if(dt.getLines().size() != 3){
			goodSort = false;
		}
		//Check for the first line
		if(dt.getLines().get(0).getElementByIndex(0).compareTo(new Element<String>("2")) != 0
				|| dt.getLines().get(0).getElementByIndex(1).compareTo(new Element<String>("Audrey")) != 0
				|| dt.getLines().get(0).getElementByIndex(2).compareTo(new Element<String>("1870")) != 0){
			goodSort = false;
		}
		//Check for the second line
		if(dt.getLines().get(1).getElementByIndex(0).compareTo(new Element<String>("3")) != 0
				|| dt.getLines().get(1).getElementByIndex(1).compareTo(new Element<String>("Audrey")) != 0
				|| dt.getLines().get(1).getElementByIndex(2).compareTo(new Element<String>("2010")) != 0){
			goodSort = false;
		}
		//Check for the third line
		if(dt.getLines().get(2).getElementByIndex(0).compareTo(new Element<String>("1")) != 0
				|| dt.getLines().get(2).getElementByIndex(1).compareTo(new Element<String>("Marion")) != 0
				|| dt.getLines().get(2).getElementByIndex(2).compareTo(new Element<String>("1995")) != 0){
			goodSort = false;
		}
		assertTrue(goodSort);
	}

	@Test
	public void testOrderByIntListLabelExceptionNotExcepted() throws ExceptionSizeNotEqual, ExceptionWrongIndex, ExceptionOperationOnEmptyTable {
		Dataframe dt = new Dataframe();

		ArrayList<Element> listOfElementLabel = new ArrayList<>();
		listOfElementLabel.add(new Element("Sexe"));
		listOfElementLabel.add(new Element("Name"));
		listOfElementLabel.add(new Element("Date"));
		Line lLabel = new Line(0, listOfElementLabel);
		dt.changeLabelLine(lLabel);

		ArrayList<Element> listOfElement = new ArrayList<>();
		listOfElement.add(new Element("2"));
		listOfElement.add(new Element("Audrey"));
		listOfElement.add(new Element("1995"));
		Line l = new Line(0, listOfElement);
		ArrayList<Element> listOfElement2 = new ArrayList<>();
		listOfElement2.add(new Element("3"));
		listOfElement2.add(new Element("Audrey"));
		listOfElement2.add(new Element("1870"));
		Line l2 = new Line(1, listOfElement2);
		dt.addLine(l);
		dt.addLine(l2);

		ArrayList<String> listOfColumn = new ArrayList<>();
		listOfColumn.add("Name");
		listOfColumn.add("Date");

		dt.orderByLabel(listOfColumn);
		boolean goodSort = true;
		if(dt.getLines().size() != 2){
			goodSort = false;
		}
		//Check for the first line
		if(dt.getLines().get(0).getElementByIndex(0).compareTo(new Element<String>("3")) != 0
				|| dt.getLines().get(0).getElementByIndex(1).compareTo(new Element<String>("Audrey")) != 0
				|| dt.getLines().get(0).getElementByIndex(2).compareTo(new Element<String>("1870")) != 0){
			goodSort = false;
		}
		//Check for the second line
		if(dt.getLines().get(1).getElementByIndex(0).compareTo(new Element<String>("2")) != 0
				|| dt.getLines().get(1).getElementByIndex(1).compareTo(new Element<String>("Audrey")) != 0
				|| dt.getLines().get(1).getElementByIndex(2).compareTo(new Element<String>("1995")) != 0){
			goodSort = false;
		}
		assertTrue(goodSort);
	}

	@Test
	public void testOrderByListLabelSameValue() throws ExceptionSizeNotEqual, ExceptionWrongIndex, ExceptionOperationOnEmptyTable  {
		Dataframe dt = new Dataframe();

		ArrayList<Element> listOfElementLabel = new ArrayList<>();
		listOfElementLabel.add(new Element("Sexe"));
		listOfElementLabel.add(new Element("Name"));
		listOfElementLabel.add(new Element("Date"));
		Line lLabel = new Line(0, listOfElementLabel);
		dt.changeLabelLine(lLabel);

		ArrayList<Element> listOfElement = new ArrayList<>();
		listOfElement.add(new Element("1"));
		listOfElement.add(new Element("Marion"));
		listOfElement.add(new Element("1995"));
		Line l = new Line(0, listOfElement);
		ArrayList<Element> listOfElement2 = new ArrayList<>();
		listOfElement2.add(new Element("2"));
		listOfElement2.add(new Element("Audrey"));
		listOfElement2.add(new Element("1870"));
		Line l2 = new Line(1, listOfElement2);
		ArrayList<Element> listOfElement3 = new ArrayList<>();
		listOfElement3.add(new Element("2"));
		listOfElement3.add(new Element("Audrey"));
		listOfElement3.add(new Element("2010"));
		Line l3 = new Line(2, listOfElement3);
		dt.addLine(l);
		dt.addLine(l2);
		dt.addLine(l3);

		ArrayList<String> listOfColumn = new ArrayList<>();
		listOfColumn.add("Name");
		listOfColumn.add("Sexe");

		dt.orderByLabel(listOfColumn);
		boolean goodSort = true;
		if(dt.getLines().size() != 3){
			goodSort = false;
		}
		//Check for the first line
		if(dt.getLines().get(0).getElementByIndex(0).compareTo(new Element<String>("2")) != 0
				|| dt.getLines().get(0).getElementByIndex(1).compareTo(new Element<String>("Audrey")) != 0
				|| dt.getLines().get(0).getElementByIndex(2).compareTo(new Element<String>("1870")) != 0){
			goodSort = false;
		}
		//Check for the second line
		if(dt.getLines().get(1).getElementByIndex(0).compareTo(new Element<String>("2")) != 0
				|| dt.getLines().get(1).getElementByIndex(1).compareTo(new Element<String>("Audrey")) != 0
				|| dt.getLines().get(1).getElementByIndex(2).compareTo(new Element<String>("2010")) != 0){
			goodSort = false;
		}
		//Check for the third line
		if(dt.getLines().get(2).getElementByIndex(0).compareTo(new Element<String>("1")) != 0
				|| dt.getLines().get(2).getElementByIndex(1).compareTo(new Element<String>("Marion")) != 0
				|| dt.getLines().get(2).getElementByIndex(2).compareTo(new Element<String>("1995")) != 0){
			goodSort = false;
		}
		assertTrue(goodSort);
	}

	@Test(expected = ExceptionWrongIndex.class)
	public void testOrderByIntNotInListLabel() throws ExceptionSizeNotEqual, ExceptionWrongIndex, ExceptionOperationOnEmptyTable  {
		Dataframe dt = new Dataframe();

		ArrayList<Element> listOfElementLabel = new ArrayList<>();
		listOfElementLabel.add(new Element("Sexe"));
		listOfElementLabel.add(new Element("Name"));
		listOfElementLabel.add(new Element("Date"));
		Line lLabel = new Line(0, listOfElementLabel);
		dt.changeLabelLine(lLabel);

		ArrayList<Element> listOfElement = new ArrayList<>();
		listOfElement.add(new Element("1"));
		listOfElement.add(new Element("Marion"));
		listOfElement.add(new Element("1995"));
		Line l = new Line(0, listOfElement);
		ArrayList<Element> listOfElement2 = new ArrayList<>();
		listOfElement2.add(new Element("2"));
		listOfElement2.add(new Element("Bruno"));
		listOfElement2.add(new Element("1870"));
		Line l2 = new Line(1, listOfElement2);
		ArrayList<Element> listOfElement3 = new ArrayList<>();
		listOfElement3.add(new Element("3"));
		listOfElement3.add(new Element("Audrey"));
		listOfElement3.add(new Element("2010"));
		Line l3 = new Line(2, listOfElement3);
		dt.addLine(l);
		dt.addLine(l2);
		dt.addLine(l3);

		ArrayList<String> listOfColumn = new ArrayList<>();
		listOfColumn.add("Sexe");
		listOfColumn.add("Faux");

		dt.orderByLabel(listOfColumn);

		boolean goodSort = true;
		if(dt.getLines().size() != 3){
			goodSort = false;
		}
		//Check for the first line
		if(dt.getLines().get(0).getElementByIndex(0).compareTo(new Element<String>("1")) != 0
				|| dt.getLines().get(0).getElementByIndex(1).compareTo(new Element<String>("Marion")) != 0
				|| dt.getLines().get(0).getElementByIndex(2).compareTo(new Element<String>("1995")) != 0){
			goodSort = false;
		}
		//Check for the second line
		if(dt.getLines().get(1).getElementByIndex(0).compareTo(new Element<String>("2")) != 0
				|| dt.getLines().get(1).getElementByIndex(1).compareTo(new Element<String>("Bruno")) != 0
				|| dt.getLines().get(1).getElementByIndex(2).compareTo(new Element<String>("1870")) != 0){
			goodSort = false;
		}
		//Check for the third line
		if(dt.getLines().get(2).getElementByIndex(0).compareTo(new Element<String>("3")) != 0
				|| dt.getLines().get(2).getElementByIndex(1).compareTo(new Element<String>("Audrey")) != 0
				|| dt.getLines().get(2).getElementByIndex(2).compareTo(new Element<String>("2010")) != 0){
			goodSort = false;
		}
		assertTrue(goodSort);
	}

}
