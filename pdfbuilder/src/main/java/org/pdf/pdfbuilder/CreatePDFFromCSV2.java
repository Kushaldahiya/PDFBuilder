package org.pdf.pdfbuilder;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.File;

import org.apache.pdfbox.pdmodel.PDPageContentStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.util.Matrix;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.plot.PiePlot;

import java.util.Scanner;

public class CreatePDFFromCSV2 {

	private static final int CHART_WIDTH = 370;
	private static final int CHART_HEIGHT = 204;
	//CreatePDF(int section) throws IOException{
	
	public static void main(String[] args) throws IOException {
		List<String[]> csvData = loadDataFromCSV("C:\\Users\\kusha\\OneDrive\\Desktop\\emaildata.csv");

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of subjects: ");
		int section = sc.nextInt();
		 sc.nextLine(); 
		
		String FirstSubject = null;
		String SecondSubject = null;
		String ThirdSubject=null;
		String FourthSubject=null;
		String FifthSubject =null;
		
		if(section==1) {
			System.out.println("Enter the Subject: ");
			String onesubject = sc.next();
			FirstSubject=onesubject;
		}
		else if(section==2) {

			System.out.println("Enter the First Subject: ");
			FirstSubject=sc.nextLine();
			System.out.println("Enter the Second Subject: ");
			SecondSubject=sc.nextLine();
				
		}
		
		else if(section==3) {
			System.out.println("Enter the First Subject: ");
			FirstSubject=sc.nextLine();
			System.out.println("Enter the Second Subject: ");
			SecondSubject=sc.nextLine();
			System.out.println("Enter the Third Subject: ");
			ThirdSubject=sc.nextLine();
		}
		else if(section==4) {
			System.out.println("Enter the First Subject: ");
			FirstSubject=sc.nextLine();
			System.out.println("Enter the Second Subject: ");
			SecondSubject=sc.nextLine();
			System.out.println("Enter the Third Subject: ");
			ThirdSubject=sc.nextLine();
			System.out.println("Enter the Fourth Subject: ");
			FourthSubject=sc.nextLine();
		}
		else if(section==5) {
			System.out.println("Enter the First Subject: ");
			FirstSubject=sc.nextLine();
			System.out.println("Enter the Second Subject: ");
			SecondSubject=sc.nextLine();
			System.out.println("Enter the Third Subject: ");
			ThirdSubject=sc.nextLine();
			System.out.println("Enter the Fourth Subject: ");
			FourthSubject=sc.nextLine();
			System.out.println("Enter the Fifth Subject: ");
			FifthSubject=sc.nextLine();
		}
		
		
		

		// Extract file name without extension
		String filePath = "C:\\\\Users\\\\kusha\\\\OneDrive\\\\Desktop\\\\Test Name.csv";

		File file = new File(filePath);
		String fileNameWithExtension = file.getName();
		String fileNameWithoutExtension = fileNameWithExtension.substring(0, fileNameWithExtension.lastIndexOf('.'));
		
		/*System.out.println("Enter the Student ID: ");
		int studentID=sc.nextInt();*/

		if (!csvData.isEmpty() && csvData.size() > 1) {
			for (int j = 1; j < csvData.size(); j++) {
				String[] firstStudent = csvData.get(j);
				String imageName = firstStudent[0].replaceAll("\\.jpg", "");

				int totalStudents = csvData.size() - 1;
				int totalScoredMarks = 0;

				List<StudentScore> studentScores = new ArrayList<>();

				List<StudentPhyScore> studentphyScores = new ArrayList<>();
				List<StudentChemScore> studentchemScores = new ArrayList<>();
				List<StudentMathScore> studentmathScores = new ArrayList<>();
				List<StudentSansScore> studentsansScores = new ArrayList<>();
				List<StudentEngScore> studentengScores = new ArrayList<>();

				List<StudentPositiveScore> studentpositiveScores = new ArrayList<>();
				List<StudentNegativeScore> studentnegativeScores = new ArrayList<>();

				List<StudentPhysicsPositiveScore> studentphysicspositiveScores = new ArrayList<>();
				List<StudentChemPositiveScore> studentchempositiveScores = new ArrayList<>();
				List<StudentMathPositiveScore> studentmathpositiveScores = new ArrayList<>();
				List<StudentSansPositiveScore> studentsanspositiveScores = new ArrayList<>();
				List<StudentEngPositiveScore> studentengpositiveScores = new ArrayList<>();

				List<StudentPhysicsNegativeScore> studentphysicsnegativeScores = new ArrayList<>();
				List<StudentChemNegativeScore> studentchemnegativeScores = new ArrayList<>();
				List<StudentMathNegativeScore> studentmathnegativeScores = new ArrayList<>();
				List<StudentSansNegativeScore> studentsansnegativeScores = new ArrayList<>();
				List<StudentEngNegativeScore> studentengnegativeScores = new ArrayList<>();

				for (int i = 1; i < csvData.size(); i++) {
					String[] studentData = csvData.get(i);

					int scoredMarks = Integer.parseInt(studentData[30]);
					String imageName1 = studentData[0].replaceAll("\\.jpg", "");

					totalScoredMarks += scoredMarks;

					studentScores.add(new StudentScore(imageName1, scoredMarks));
				}

				int totalPhyScoredMarks = 0;

				for (int i = 1; i < csvData.size(); i++) {

					String[] studentData = csvData.get(i);
					String imageName1 = studentData[0].replaceAll("\\.jpg", "");

					int scoredMarks = Integer.parseInt(studentData[4]);

					totalPhyScoredMarks += scoredMarks;

					studentphyScores.add(new StudentPhyScore(imageName1, scoredMarks));

				}

				int totalChemScoredMarks = 0;

				for (int i = 1; i < csvData.size(); i++) {
					String[] studentData = csvData.get(i);
					String imageName1 = studentData[0].replaceAll("\\.jpg", "");

					int scoredMarks = Integer.parseInt(studentData[9]);

					totalChemScoredMarks += scoredMarks;

					studentchemScores.add(new StudentChemScore(imageName1, scoredMarks));

				}

				int totalMathScoredMarks = 0;

				for (int i = 1; i < csvData.size(); i++) {
					String[] studentData = csvData.get(i);
					String imageName1 = studentData[0].replaceAll("\\.jpg", "");

					int scoredMarks = Integer.parseInt(studentData[14]);

					totalMathScoredMarks += scoredMarks;

					studentmathScores.add(new StudentMathScore(imageName1, scoredMarks));

				}
				int totalSansScoredMarks = 0;

				for (int i = 1; i < csvData.size(); i++) {
					String[] studentData = csvData.get(i);
					String imageName1 = studentData[0].replaceAll("\\.jpg", "");

					int scoredMarks = Integer.parseInt(studentData[19]);

					totalSansScoredMarks += scoredMarks;

					studentsansScores.add(new StudentSansScore(imageName1, scoredMarks));

				}
				int totalEngScoredMarks = 0;

				for (int i = 1; i < csvData.size(); i++) {
					String[] studentData = csvData.get(i);
					String imageName1 = studentData[0].replaceAll("\\.jpg", "");

					int scoredMarks = Integer.parseInt(studentData[14]);

					totalEngScoredMarks += scoredMarks;

					studentengScores.add(new StudentEngScore(imageName1, scoredMarks));

				}
				int totalPositiveScore = 0;
				for (int i = 1; i < csvData.size(); i++) {
					String[] studentData = csvData.get(i);
					int positivescore = Integer.parseInt(studentData[16]);
					totalPositiveScore += positivescore;
					studentpositiveScores.add(new StudentPositiveScore(imageName, positivescore));
				}
				int totalNegativeScore = 0;
				for (int i = 1; i < csvData.size(); i++) {
					String[] studentData = csvData.get(i);
					int negativescore = Integer.parseInt(studentData[17]);
					totalNegativeScore += negativescore;
					studentnegativeScores.add(new StudentNegativeScore(imageName, negativescore));
				}

				int totalPhysicsPositiveScore = 0;
				for (int i = 1; i < csvData.size(); i++) {
					String[] studentData = csvData.get(i);
					int positivescore = Integer.parseInt(studentData[1]);
					totalPhysicsPositiveScore += positivescore;
					studentphysicspositiveScores.add(new StudentPhysicsPositiveScore(imageName, positivescore));
				}
				Collections.sort(studentphysicspositiveScores,
						Comparator.comparingInt(StudentPhysicsPositiveScore::getScore).reversed());
				List<StudentPhysicsPositiveScore> top5PhysicsPositive = studentphysicspositiveScores.subList(0,
						Math.min(5, studentScores.size()));

				int totalChemPositiveScore = 0;
				for (int i = 1; i < csvData.size(); i++) {
					String[] studentData = csvData.get(i);
					int positivescore = Integer.parseInt(studentData[6]);
					totalChemPositiveScore += positivescore;
					studentchempositiveScores.add(new StudentChemPositiveScore(imageName, positivescore));
				}
				Collections.sort(studentchempositiveScores,
						Comparator.comparingInt(StudentChemPositiveScore::getScore).reversed());
				List<StudentChemPositiveScore> top5ChemPositive = studentchempositiveScores.subList(0,
						Math.min(5, studentScores.size()));

				int totalMathPositiveScore = 0;
				for (int i = 1; i < csvData.size(); i++) {
					String[] studentData = csvData.get(i);
					int positivescore = Integer.parseInt(studentData[11]);
					totalMathPositiveScore += positivescore;
					studentmathpositiveScores.add(new StudentMathPositiveScore(imageName, positivescore));
				}
				Collections.sort(studentmathpositiveScores,
						Comparator.comparingInt(StudentMathPositiveScore::getScore).reversed());
				List<StudentMathPositiveScore> top5MathPositive = studentmathpositiveScores.subList(0,
						Math.min(5, studentScores.size()));

				int totalSansPositiveScore = 0;
				for (int i = 1; i < csvData.size(); i++) {
					String[] studentData = csvData.get(i);
					int positivescore = Integer.parseInt(studentData[16]);
					totalSansPositiveScore += positivescore;
					studentsanspositiveScores.add(new StudentSansPositiveScore(imageName, positivescore));
				}
				Collections.sort(studentsanspositiveScores,
						Comparator.comparingInt(StudentSansPositiveScore::getScore).reversed());
				List<StudentSansPositiveScore> top5SansPositive = studentsanspositiveScores.subList(0,
						Math.min(5, studentScores.size()));

				int totalEngPositiveScore = 0;
				for (int i = 1; i < csvData.size(); i++) {
					String[] studentData = csvData.get(i);
					int positivescore = Integer.parseInt(studentData[21]);
					totalEngPositiveScore += positivescore;
					studentengpositiveScores.add(new StudentEngPositiveScore(imageName, positivescore));
				}
				Collections.sort(studentengpositiveScores,
						Comparator.comparingInt(StudentEngPositiveScore::getScore).reversed());
				List<StudentEngPositiveScore> top5EngPositive = studentengpositiveScores.subList(0,
						Math.min(5, studentScores.size()));

				int totalPhysicsNegativeScore = 0;
				for (int i = 1; i < csvData.size(); i++) {
					String[] studentData = csvData.get(i);
					int positivescore = Integer.parseInt(studentData[2]);
					totalPhysicsNegativeScore += positivescore;
					studentphysicsnegativeScores.add(new StudentPhysicsNegativeScore(imageName, positivescore));
				}
				Collections.sort(studentphysicsnegativeScores,
						Comparator.comparingInt(StudentPhysicsNegativeScore::getScore).reversed());
				List<StudentPhysicsNegativeScore> top5PhysicsNegative = studentphysicsnegativeScores.subList(0,
						Math.min(5, studentScores.size()));

				int totalChemNegativeScore = 0;
				for (int i = 1; i < csvData.size(); i++) {
					String[] studentData = csvData.get(i);
					int positivescore = Integer.parseInt(studentData[7]);
					totalChemNegativeScore += positivescore;
					studentchemnegativeScores.add(new StudentChemNegativeScore(imageName, positivescore));
				}
				Collections.sort(studentchemnegativeScores,
						Comparator.comparingInt(StudentChemNegativeScore::getScore).reversed());
				List<StudentChemNegativeScore> top5ChemNegative = studentchemnegativeScores.subList(0,
						Math.min(5, studentScores.size()));

				int totalMathNegativeScore = 0;
				for (int i = 1; i < csvData.size(); i++) {
					String[] studentData = csvData.get(i);
					int positivescore = Integer.parseInt(studentData[12]);
					totalMathNegativeScore += positivescore;
					studentmathnegativeScores.add(new StudentMathNegativeScore(imageName, positivescore));
				}
				Collections.sort(studentmathnegativeScores,
						Comparator.comparingInt(StudentMathNegativeScore::getScore).reversed());
				List<StudentMathNegativeScore> top5MathNegative = studentmathnegativeScores.subList(0,
						Math.min(5, studentScores.size()));

				int totalSansNegativeScore = 0;
				for (int i = 1; i < csvData.size(); i++) {
					String[] studentData = csvData.get(i);
					int positivescore = Integer.parseInt(studentData[17]);
					totalSansNegativeScore += positivescore;
					studentsansnegativeScores.add(new StudentSansNegativeScore(imageName, positivescore));
				}
				Collections.sort(studentsansnegativeScores,
						Comparator.comparingInt(StudentSansNegativeScore::getScore).reversed());
				List<StudentSansNegativeScore> top5SansNegative = studentsansnegativeScores.subList(0,
						Math.min(5, studentScores.size()));

				int totalEngNegativeScore = 0;
				for (int i = 1; i < csvData.size(); i++) {
					String[] studentData = csvData.get(i);
					int positivescore = Integer.parseInt(studentData[22]);
					totalEngNegativeScore += positivescore;
					studentengnegativeScores.add(new StudentEngNegativeScore(imageName, positivescore));
				}
				Collections.sort(studentengnegativeScores,
						Comparator.comparingInt(StudentEngNegativeScore::getScore).reversed());
				List<StudentEngNegativeScore> top5EngNegative = studentengnegativeScores.subList(0,
						Math.min(5, studentScores.size()));

				Collections.sort(studentnegativeScores,
						Comparator.comparingInt(StudentNegativeScore::getScore).reversed());
				List<StudentNegativeScore> top5Negative = studentnegativeScores.subList(0,
						Math.min(5, studentScores.size()));
				Collections.sort(studentpositiveScores,
						Comparator.comparingInt(StudentPositiveScore::getScore).reversed());
				List<StudentPositiveScore> top5Positive = studentpositiveScores.subList(0,
						Math.min(5, studentScores.size()));

				int averageSansScoredMarks = (int) totalSansScoredMarks / totalStudents;

				Collections.sort(studentsansScores, Comparator.comparingInt(StudentSansScore::getScore).reversed());

				for (int i = 0; i < totalStudents; i++) {
					studentsansScores.get(i).setRank(i + 1);
				}

				List<StudentSansScore> top5sansStudents = studentsansScores.subList(0,
						Math.min(5, studentScores.size()));

				int averageEngScoredMarks = (int) totalEngScoredMarks / totalStudents;

				Collections.sort(studentengScores, Comparator.comparingInt(StudentEngScore::getScore).reversed());

				for (int i = 0; i < totalStudents; i++) {
					studentengScores.get(i).setRank(i + 1);
				}

				List<StudentEngScore> top5engStudents = studentengScores.subList(0, Math.min(5, studentScores.size()));

				int averageMathScoredMarks = (int) totalMathScoredMarks / totalStudents;

				Collections.sort(studentmathScores, Comparator.comparingInt(StudentMathScore::getScore).reversed());

				for (int i = 0; i < totalStudents; i++) {
					studentmathScores.get(i).setRank(i + 1);
				}

				List<StudentMathScore> top5mathStudents = studentmathScores.subList(0,
						Math.min(5, studentScores.size()));

				int averageChemScoredMarks = ((int) totalChemScoredMarks / totalStudents);

				Collections.sort(studentchemScores, Comparator.comparingInt(StudentChemScore::getScore).reversed());

				for (int i = 0; i < totalStudents; i++) {
					studentchemScores.get(i).setRank(i + 1);
				}

				List<StudentChemScore> top5chemStudents = studentchemScores.subList(0,
						Math.min(5, studentScores.size()));

				int averagePhyScoredMarks = (int) totalPhyScoredMarks / totalStudents;

				Collections.sort(studentphyScores, Comparator.comparingInt(StudentPhyScore::getScore).reversed());

				for (int i = 0; i < totalStudents; i++) {
					studentphyScores.get(i).setRank(i + 1);
				}

				List<StudentPhyScore> top5phyStudents = studentphyScores.subList(0, Math.min(5, studentScores.size()));

				// avg score
				double averageScoredMarks = (double) totalScoredMarks / totalStudents;

				Collections.sort(studentScores, Comparator.comparingInt(StudentScore::getScore).reversed());

				List<StudentScore> top5Students = studentScores.subList(0, Math.min(5, studentScores.size()));

				int physicsScore1 = Integer.parseInt(firstStudent[1]);
				int physicsScore2 = Integer.parseInt(firstStudent[2]);
				int physicsScore3 = Integer.parseInt(firstStudent[3]);
				int totalPhysicsScore = physicsScore1 + physicsScore2 + physicsScore3;

				int physicsMarks = Integer.parseInt(firstStudent[4]);
				String physicspercentage = firstStudent[5];

				int chemistryScore1 = Integer.parseInt(firstStudent[6]);
				int chemistryScore2 = Integer.parseInt(firstStudent[7]);
				int chemistryScore3 = Integer.parseInt(firstStudent[8]);
				int totalchemistryScore = chemistryScore1 + chemistryScore2 + chemistryScore3;

				int chemistryMarks = Integer.parseInt(firstStudent[9]);
				String chemistrypercentage = firstStudent[10];

				int mathsScore1 = Integer.parseInt(firstStudent[11]);
				int mathsScore2 = Integer.parseInt(firstStudent[12]);
				int mathsScore3 = Integer.parseInt(firstStudent[13]);
				int totalmathsScore = mathsScore1 + mathsScore2 + mathsScore3;

				int mathsMarks = Integer.parseInt(firstStudent[14]);
				String mathspercentage = firstStudent[15];

				int sansScore1 = Integer.parseInt(firstStudent[16]);
				int sansScore2 = Integer.parseInt(firstStudent[17]);
				int sansScore3 = Integer.parseInt(firstStudent[18]);
				int totalsansScore = sansScore1 + sansScore2 + sansScore3;

				int sansMarks = Integer.parseInt(firstStudent[19]);
				String sanspercentage = firstStudent[20];

				int engScore1 = Integer.parseInt(firstStudent[21]);
				int engScore2 = Integer.parseInt(firstStudent[22]);
				int engScore3 = Integer.parseInt(firstStudent[23]);
				int totalengScore = engScore1 + engScore2 + engScore3;

				int engMarks = Integer.parseInt(firstStudent[24]);
				String engpercentage = firstStudent[25];

				String scoredMarks = firstStudent[30];
				String maxMarks = firstStudent[29];
				String percentage = firstStudent[31];

				String correct = firstStudent[26];
				String incorrect = firstStudent[27];
				String unattempted = firstStudent[28];

				String phyScoredmarks = firstStudent[4];
				int PhyMaxMarks = Integer.parseInt(firstStudent[1]) + Integer.parseInt(firstStudent[2])
						+ Integer.parseInt(firstStudent[3]);

				String chemScoredmarks = firstStudent[9];

				String mathsScoredmarks = firstStudent[14];
				String sansScoredmarks = firstStudent[19];

				String engScoredmarks = firstStudent[24];

				String PositiveScore = firstStudent[26];
				String NegativeScore = firstStudent[27];

				int attemptmathQuestions = totalmathsScore - mathsScore3;
				int attemptphysicsQuestions = totalPhysicsScore - physicsScore3;

				int attemptchemistryQuestions = totalchemistryScore - chemistryScore3;
				int attempsansQuestions = totalsansScore - sansScore3;
				int attemptengQuestions = totalengScore - engScore3;

				if (section == 5) {

					try (PDDocument document = new PDDocument()) {

						PDPage page = new PDPage();
						document.addPage(page);
						PDPage page2 = new PDPage();
						document.addPage(page2);
						PDPage page3 = new PDPage();
						document.addPage(page3);
						PDPage page4 = new PDPage();
						document.addPage(page4);
						PDPage page5 = new PDPage();
						document.addPage(page5);
						

						try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

							addContentToPage1Section5(document, page, contentStream, imageName, totalStudents,
									scoredMarks, maxMarks, percentage, physicsMarks, totalPhysicsScore,
									physicspercentage, chemistryMarks, totalchemistryScore, chemistrypercentage,
									mathsMarks, totalmathsScore, mathspercentage, fileNameWithoutExtension, sansMarks,
									totalsansScore, sanspercentage, engMarks, totalengScore, engpercentage,FirstSubject,SecondSubject,ThirdSubject,FourthSubject,FifthSubject);

							addPageNumber(document, imageName, contentStream, 1);

						}

						try (PDPageContentStream contentStream = new PDPageContentStream(document, page2)) {

							addContentToPage2Section5(page2, contentStream, scoredMarks, document, maxMarks,
									averageScoredMarks, top5Students, correct, incorrect, unattempted, phyScoredmarks,
									averagePhyScoredMarks, top5phyStudents, chemScoredmarks, PhyMaxMarks,
									top5chemStudents, averageChemScoredMarks, top5mathStudents, mathsScoredmarks,
									averageMathScoredMarks, sansScoredmarks, averageSansScoredMarks, top5sansStudents,
									engScoredmarks, averageEngScoredMarks, top5engStudents,FirstSubject,SecondSubject,ThirdSubject,FourthSubject,FifthSubject);

							addPageNumber(document, imageName, contentStream, 2);
						}
						try (PDPageContentStream contentStream = new PDPageContentStream(document, page3)) {

							addContentTopage3Section5(imageName, page, contentStream, document, totalPhysicsScore,
									totalchemistryScore, totalmathsScore, physicsMarks, chemistryMarks, mathsMarks,
									attemptmathQuestions, mathsScore1, mathsScore2, mathspercentage,
									attemptphysicsQuestions, physicsScore1, physicsScore2, physicspercentage,
									attemptchemistryQuestions, chemistryScore1, chemistryScore2, chemistrypercentage,
									studentmathScores, studentphyScores, studentchemScores, physicsScore3,
									chemistryScore3, mathsScore3, top5Positive, PositiveScore, top5Negative,
									NegativeScore, totalsansScore, totalengScore, sansMarks, engMarks,
									attempsansQuestions, sansScore1, sansScore2, sansScore3, sanspercentage,
									attemptengQuestions, engScore1, engScore2, engScore3, engpercentage,
									studentsansScores, studentengScores, maxMarks,FirstSubject,SecondSubject,ThirdSubject,FourthSubject,FifthSubject);

							addPageNumber(document, imageName, contentStream, 3);
						}

						try (PDPageContentStream contentStream = new PDPageContentStream(document, page4)) {

							addContentTopage4Section5(contentStream, document, physicsScore1, chemistryScore1,
									mathsScore1, physicsScore2, chemistryScore2, mathsScore2, top5PhysicsPositive,
									top5ChemPositive, top5MathPositive, top5PhysicsNegative, top5ChemNegative,
									top5MathNegative, sansScore1, engScore1, sansScore2, engScore2, top5SansPositive,
									top5EngPositive, top5SansNegative, top5EngNegative,FirstSubject,SecondSubject,ThirdSubject,FourthSubject,FifthSubject);

							addPageNumber(document, imageName, contentStream, 4);
						}
						try (PDPageContentStream contentStream = new PDPageContentStream(document, page5)) {

							addContentTopage5Section5(contentStream, document, physicsScore1, chemistryScore1,
									mathsScore1, physicsScore2, chemistryScore2, mathsScore2, top5PhysicsPositive,
									top5ChemPositive, top5MathPositive, top5PhysicsNegative, top5ChemNegative,
									top5MathNegative, sansScore1, engScore1, sansScore2, engScore2, top5SansPositive,
									top5EngPositive, top5SansNegative, top5EngNegative,FirstSubject,SecondSubject,ThirdSubject,FourthSubject,FifthSubject);

							addPageNumber(document, imageName, contentStream, 5);
						}

						String fileName = "C:\\Users\\kusha\\OneDrive\\Desktop\\test\\" + imageName + ".pdf";
						document.save(fileName);
					}
				}

				else if (section == 4) {
					try (PDDocument document = new PDDocument()) {

						PDPage page = new PDPage();
						document.addPage(page);
						PDPage page2 = new PDPage();
						document.addPage(page2);
						PDPage page3 = new PDPage();
						document.addPage(page3);
						PDPage page4 = new PDPage();
						document.addPage(page4);
						PDPage page5 = new PDPage();
						document.addPage(page5);

						try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

							addContentToPage1Section4(document, page, contentStream, imageName, totalStudents,
									scoredMarks, maxMarks, percentage, physicsMarks, totalPhysicsScore,
									physicspercentage, chemistryMarks, totalchemistryScore, chemistrypercentage,
									mathsMarks, totalmathsScore, mathspercentage, fileNameWithoutExtension, sansMarks,
									totalsansScore, sanspercentage,FirstSubject,SecondSubject,ThirdSubject,FourthSubject);

							addPageNumber(document, imageName, contentStream, 1);

						}

						try (PDPageContentStream contentStream = new PDPageContentStream(document, page2)) {

							addContentToPage2Section4(page2, contentStream, scoredMarks, document, maxMarks,
									averageScoredMarks, top5Students, correct, incorrect, unattempted, phyScoredmarks,
									averagePhyScoredMarks, top5phyStudents, chemScoredmarks, PhyMaxMarks,
									top5chemStudents, averageChemScoredMarks, top5mathStudents, mathsScoredmarks,
									averageMathScoredMarks, sansScoredmarks, averageSansScoredMarks, top5sansStudents,FirstSubject,SecondSubject,ThirdSubject,FourthSubject);

							addPageNumber(document, imageName, contentStream, 2);
						}
						try (PDPageContentStream contentStream = new PDPageContentStream(document, page3)) {

							addContentTopage3Section4(imageName, page, contentStream, document, totalPhysicsScore,
									totalchemistryScore, totalmathsScore, physicsMarks, chemistryMarks, mathsMarks,
									attemptmathQuestions, mathsScore1, mathsScore2, mathspercentage,
									attemptphysicsQuestions, physicsScore1, physicsScore2, physicspercentage,
									attemptchemistryQuestions, chemistryScore1, chemistryScore2, chemistrypercentage,
									studentmathScores, studentphyScores, studentchemScores, physicsScore3,
									chemistryScore3, mathsScore3, top5Positive, PositiveScore, top5Negative,
									NegativeScore, totalsansScore, sansMarks, attempsansQuestions, sansScore1,
									sansScore2, sansScore3, sanspercentage, studentsansScores, maxMarks,FirstSubject,SecondSubject,ThirdSubject,FourthSubject);

							addPageNumber(document, imageName, contentStream, 3);
						}

						try (PDPageContentStream contentStream = new PDPageContentStream(document, page4)) {

							addContentTopage4Section4(contentStream, document, physicsScore1, chemistryScore1,
									mathsScore1, physicsScore2, chemistryScore2, mathsScore2, top5PhysicsPositive,
									top5ChemPositive, top5MathPositive, top5PhysicsNegative, top5ChemNegative,
									top5MathNegative, sansScore1, sansScore2, top5SansPositive, top5SansNegative,FirstSubject,SecondSubject,ThirdSubject,FourthSubject);

							addPageNumber(document, imageName, contentStream, 4);
						}
						try (PDPageContentStream contentStream = new PDPageContentStream(document, page5)) {

							addContentTopage5Section4(contentStream, document, physicsScore1, chemistryScore1,
									mathsScore1, physicsScore2, chemistryScore2, mathsScore2, top5PhysicsPositive,
									top5ChemPositive, top5MathPositive, top5PhysicsNegative, top5ChemNegative,
									top5MathNegative, sansScore1, sansScore2, top5SansPositive, top5SansNegative,FirstSubject,SecondSubject,ThirdSubject,FourthSubject);

							addPageNumber(document, imageName, contentStream, 5);
						}

						String fileName = "C:\\Users\\kusha\\OneDrive\\Desktop\\test\\student_" + imageName + ".pdf";
						document.save(fileName);
					}
				}

				else if (section == 3) {

					try (PDDocument document = new PDDocument()) {

						PDPage page = new PDPage();
						document.addPage(page);
						PDPage page2 = new PDPage();
						document.addPage(page2);
						PDPage page3 = new PDPage();
						document.addPage(page3);
						PDPage page4 = new PDPage();
						document.addPage(page4);

						try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

							addContentToPage1(document, page, contentStream, imageName, totalStudents, scoredMarks,
									maxMarks, percentage, physicsMarks, totalPhysicsScore, physicspercentage,
									chemistryMarks, totalchemistryScore, chemistrypercentage, mathsMarks,
									totalmathsScore, mathspercentage, fileNameWithoutExtension,FirstSubject,SecondSubject,ThirdSubject);

							addPageNumber(document, imageName, contentStream, 1);

						}

						try (PDPageContentStream contentStream = new PDPageContentStream(document, page2)) {

							addContentToPage2(page2, contentStream, scoredMarks, document, maxMarks, averageScoredMarks,
									top5Students, correct, incorrect, unattempted, phyScoredmarks,
									averagePhyScoredMarks, top5phyStudents, chemScoredmarks, PhyMaxMarks,
									top5chemStudents, averageChemScoredMarks, top5mathStudents, mathsScoredmarks,
									averageMathScoredMarks,FirstSubject,SecondSubject,ThirdSubject);

							addPageNumber(document, imageName, contentStream, 2);
						}

						try (PDPageContentStream contentStream = new PDPageContentStream(document, page3)) {

							addContentTopage3(imageName, page, contentStream, document, totalPhysicsScore,
									totalchemistryScore, totalmathsScore, physicsMarks, chemistryMarks, mathsMarks,
									attemptmathQuestions, mathsScore1, mathsScore2, mathspercentage,
									attemptphysicsQuestions, physicsScore1, physicsScore2, physicspercentage,
									attemptchemistryQuestions, chemistryScore1, chemistryScore2, chemistrypercentage,
									studentmathScores, studentphyScores, studentchemScores, physicsScore3,
									chemistryScore3, mathsScore3, top5Positive, PositiveScore, top5Negative,
									NegativeScore,FirstSubject,SecondSubject,ThirdSubject);

							addPageNumber(document, imageName, contentStream, 3);
						}
						try (PDPageContentStream contentStream = new PDPageContentStream(document, page4)) {

							addContentTopage4(contentStream, document, physicsScore1, chemistryScore1, mathsScore1,
									physicsScore2, chemistryScore2, mathsScore2, top5PhysicsPositive, top5ChemPositive,
									top5MathPositive, top5PhysicsNegative, top5ChemNegative, top5MathNegative,FirstSubject,SecondSubject,ThirdSubject);

							addPageNumber(document, imageName, contentStream, 4);
						}

						String fileName = "C:\\Users\\kusha\\OneDrive\\Desktop\\test\\student_" + imageName + ".pdf";
						document.save(fileName);
					}
				} else if (section == 2) {

					try (PDDocument document = new PDDocument()) {

						PDPage page = new PDPage();
						document.addPage(page);
						PDPage page2 = new PDPage();
						document.addPage(page2);
						PDPage page3 = new PDPage();
						document.addPage(page3);
						PDPage page4 = new PDPage();
						document.addPage(page4);

						try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

							addContentToPage1Section2(document, page, contentStream, imageName, totalStudents,
									scoredMarks, maxMarks, percentage, physicsMarks, totalPhysicsScore,
									physicspercentage, mathsMarks, totalmathsScore, mathspercentage,
									fileNameWithoutExtension,FirstSubject,SecondSubject);

							addPageNumber(document, imageName, contentStream, 1);

						}

						try (PDPageContentStream contentStream = new PDPageContentStream(document, page2)) {

							addContentTopage3Section2(page2, contentStream, scoredMarks, document, maxMarks,
									averageScoredMarks, top5Students, correct, incorrect, unattempted, phyScoredmarks,
									averagePhyScoredMarks, top5phyStudents, PhyMaxMarks, top5mathStudents,
									mathsScoredmarks, averageMathScoredMarks,FirstSubject,SecondSubject);

							addPageNumber(document, imageName, contentStream, 2);
						}

						try (PDPageContentStream contentStream = new PDPageContentStream(document, page3)) {

							addContentTopage3Section2(imageName, page, contentStream, document, totalPhysicsScore,
									totalchemistryScore, totalmathsScore, physicsMarks, chemistryMarks, mathsMarks,
									attemptmathQuestions, mathspercentage, attemptphysicsQuestions, physicsScore1,
									physicsScore2, physicspercentage, studentmathScores, studentphyScores,
									physicsScore3, chemistryScore3, top5Positive, PositiveScore, top5Negative,
									NegativeScore,FirstSubject,SecondSubject);

							addPageNumber(document, imageName, contentStream, 3);
						}
						try (PDPageContentStream contentStream = new PDPageContentStream(document, page4)) {

							addContentTopage4Section2(contentStream, document, physicsScore1, mathsScore1,
									physicsScore2, mathsScore2, top5PhysicsPositive, top5MathPositive,
									top5PhysicsNegative, top5MathNegative,FirstSubject,SecondSubject);

							addPageNumber(document, imageName, contentStream, 4);
						}

						String fileName = "C:\\Users\\kusha\\OneDrive\\Desktop\\test\\student_" + imageName + ".pdf";
						document.save(fileName);
					}

				} else if (section == 1) {

					try (PDDocument document = new PDDocument()) {

						PDPage page = new PDPage();
						document.addPage(page);
						PDPage page2 = new PDPage();
						document.addPage(page2);
						PDPage page3 = new PDPage();
						document.addPage(page3);
						PDPage page4 = new PDPage();
						document.addPage(page4);

						try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

							addContentToPage1Section1(document, page, contentStream, imageName, totalStudents,
									scoredMarks, maxMarks, percentage, physicsMarks, totalPhysicsScore,
									physicspercentage, fileNameWithoutExtension,FirstSubject);

							addPageNumber(document, imageName, contentStream, 1);

						}

						try (PDPageContentStream contentStream = new PDPageContentStream(document, page2)) {

							addContentTopage3Section1(page2, contentStream, scoredMarks, document, maxMarks,
									averageScoredMarks, top5Students, correct, incorrect, unattempted, phyScoredmarks,
									averagePhyScoredMarks, top5phyStudents, PhyMaxMarks,FirstSubject);

							addPageNumber(document, imageName, contentStream, 2);
						}

						try (PDPageContentStream contentStream = new PDPageContentStream(document, page3)) {

							addContentTopage3Section1(imageName, page, contentStream, document, totalPhysicsScore,
									physicsMarks, attemptphysicsQuestions, physicsScore1, physicsScore2,
									physicspercentage, studentphyScores, physicsScore3, top5Positive, PositiveScore,
									top5Negative, NegativeScore,FirstSubject);

							addPageNumber(document, imageName, contentStream, 3);
						}
						try (PDPageContentStream contentStream = new PDPageContentStream(document, page4)) {

							addContentTopage4Section1(contentStream, document, physicsScore1, physicsScore2,
									top5PhysicsPositive, top5PhysicsNegative,FirstSubject);

							addPageNumber(document, imageName, contentStream, 4);
						}

						String fileName = "C:\\Users\\kusha\\OneDrive\\Desktop\\test\\student_" + imageName + ".pdf";
						document.save(fileName);
					}

				}

			}

		}
	}

	//}

	/*
	 * 
	 * 
	 * functions
	 * 
	 * 
	 * 
	 */

	public static int getStudentRank(List<StudentMathScore> studentMathScores, String imageName) {
		for (StudentMathScore student : studentMathScores) {
			if (student.getName().equals(imageName)) {
				return student.getRank();
			}
		}
		return -1;
	}

	public static int getphyStudentRank(List<StudentPhyScore> studentPhyScores, String imageName) {
		for (StudentPhyScore student : studentPhyScores) {
			if (student.getName().equals(imageName)) {
				return student.getRank();
			}
		}
		return -1;
	}

	public static int getchemtudentRank(List<StudentChemScore> studentChemScores, String imageName) {
		for (StudentChemScore student : studentChemScores) {
			if (student.getName().equals(imageName)) {
				return student.getRank();
			}
		}
		return -1;
	}

	public static int getsanstudentRank(List<StudentSansScore> studentSansScores, String imageName) {
		for (StudentSansScore student : studentSansScores) {
			if (student.getName().equals(imageName)) {
				return student.getRank();
			}
		}
		return -1;
	}

	public static int getEngStudentRank(List<StudentEngScore> studentEngScores, String imageName) {
		for (StudentEngScore student : studentEngScores) {
			if (student.getName().equals(imageName)) {
				return student.getRank();
			}
		}
		return -1;
	}

	static class StudentScore {
		private final String name;
		private final int score;

		public StudentScore(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}
	}

	static class StudentPositiveScore {
		private final String name;
		private final int score;

		public StudentPositiveScore(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}
	}

	static class StudentPhysicsPositiveScore {
		private final String name;
		private final int score;

		public StudentPhysicsPositiveScore(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}
	}

	static class StudentMathPositiveScore {
		private final String name;
		private final int score;

		public StudentMathPositiveScore(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}
	}

	static class StudentChemPositiveScore {
		private final String name;
		private final int score;

		public StudentChemPositiveScore(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}
	}

	static class StudentSansPositiveScore {
		private final String name;
		private final int score;

		public StudentSansPositiveScore(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}
	}

	static class StudentEngPositiveScore {
		private final String name;
		private final int score;

		public StudentEngPositiveScore(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}
	}

	static class StudentNegativeScore {
		private final String name;
		private final int score;

		public StudentNegativeScore(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}
	}

	static class StudentPhysicsNegativeScore {
		private final String name;
		private final int score;

		public StudentPhysicsNegativeScore(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}
	}

	static class StudentMathNegativeScore {
		private final String name;
		private final int score;

		public StudentMathNegativeScore(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}
	}

	static class StudentChemNegativeScore {
		private final String name;
		private final int score;

		public StudentChemNegativeScore(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}
	}

	static class StudentSansNegativeScore {
		private final String name;
		private final int score;

		public StudentSansNegativeScore(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}
	}

	static class StudentEngNegativeScore {
		private final String name;
		private final int score;

		public StudentEngNegativeScore(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}
	}

	static class StudentPhyScore {
		private final String name;
		private final int score;
		private int rank;

		public StudentPhyScore(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}

		public int getRank() {
			return rank;
		}
	}

	static class StudentChemScore {
		private final String name;
		private final int score;
		private int rank;

		public StudentChemScore(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}

		public int getRank() {
			return rank;
		}
	}

	static class StudentMathScore {
		private final String name;
		private final int score;
		private int rank;

		public StudentMathScore(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}

		public int getRank() {
			return rank;
		}
	}

	static class StudentSansScore {
		private final String name;
		private final int score;
		private int rank;

		public StudentSansScore(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}

		public int getRank() {
			return rank;
		}
	}

	static class StudentEngScore {
		private final String name;
		private final int score;
		private int rank;

		public StudentEngScore(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}

		public int getRank() {
			return rank;
		}
	}

	private static List<String[]> loadDataFromCSV(String csvFilePath) throws IOException {
		List<String[]> csvData = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
			String line;
			String cvsSplitBy = ",";
			while ((line = br.readLine()) != null) {
				String[] row = line.split(cvsSplitBy);
				csvData.add(row);
			}
		}
		return csvData;
	}

	private static float[] hexToRGB(String hexColor) {
		return new float[] { Integer.valueOf(hexColor.substring(1, 3), 16) / 255f,
				Integer.valueOf(hexColor.substring(3, 5), 16) / 255f,
				Integer.valueOf(hexColor.substring(5, 7), 16) / 255f };
	}

	@SuppressWarnings("unused")
	private static void addContentToPage1(PDDocument document, PDPage page, PDPageContentStream contentStream,
			String imageName, int totalStudents, String scoredMarks, String maxMarks, String percentage,
			int physicsMarks, int totalPhysicsScore, String physicspercentage, int chemistryMarks,
			int totalchemistryScore, String chemistrypercentage, int mathsMarks, int totalmathsScore,
			String mathspercentage, String fileNameWithoutExtension,String FirstSubject,String SecondSubject,String ThirdSubject) throws IOException {

		float[] textColor1 = hexToRGB("#F5C300");

		String scoreReportBelowText = fileNameWithoutExtension;
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 26);
		contentStream.setNonStrokingColor(textColor1[0], textColor1[1], textColor1[2]);
		float textWidth1 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(scoreReportBelowText)
				/ 1000f * 40;
		float textX1 = 70;
		float textY1 = 565;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX1, textY1));
		contentStream.showText(scoreReportBelowText);
		contentStream.endText();

		float[] textColor = hexToRGB("#6978aa");

		String scoreReportText = "Score Report";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 46);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(scoreReportText) / 1000f
				* 40;
		float textX = 70;
		float textY = 600;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX, textY));
		contentStream.showText(scoreReportText);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 46);
		contentStream.setNonStrokingColor(textColor[0], textColor[1], textColor[2]);
		float shadowOffsetX = 1;
		float shadowOffsetY = 1;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX + shadowOffsetX, textY - shadowOffsetY));
		contentStream.showText(scoreReportText);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 430);
		contentStream.showText(Integer.toString(totalStudents));
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 390);
		contentStream.showText(scoredMarks + "/" + maxMarks + "(" + percentage + ")");
		contentStream.endText();

		/*
		 * contentStream.beginText(); contentStream.setFont(new
		 * PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		 * contentStream.setNonStrokingColor(0f, 0f, 0f);
		 * contentStream.newLineAtOffset(425, 390); contentStream.showText(maxMarks);
		 * contentStream.endText();
		 */
		/*
		 * contentStream.beginText(); contentStream.setFont(new
		 * PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		 * contentStream.setNonStrokingColor(0f, 0f, 0f);
		 * contentStream.newLineAtOffset(443, 390);
		 * contentStream.showText("("+percentage+")"); contentStream.endText();
		 */
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(200, 355);
		contentStream.showText(FirstSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 355);
		contentStream
				.showText(physicsMarks + "/" + Integer.toString(totalPhysicsScore) + "(" + physicspercentage + ")");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(200, 325);
		contentStream.showText(SecondSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 325);
		contentStream.showText(
				chemistryMarks + "/" + Integer.toString(totalchemistryScore) + "(" + chemistrypercentage + ")");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(200, 295);
		contentStream.showText(ThirdSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 295);
		contentStream.showText(mathsMarks + "/" + Integer.toString(totalmathsScore) + "(" + mathspercentage + ")");
		contentStream.endText();

		String candidateIDText = "Candidate ID";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth2 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(candidateIDText) / 1000f
				* 40;
		float textX2 = 200;
		float textY2 = 490;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX2, textY2));
		contentStream.showText(candidateIDText);
		contentStream.endText();

		String candidateIDansText = "5000041";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth3 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(candidateIDansText)
				/ 1000f * 40;
		float textX3 = 400;
		float textY3 = 490;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX3, textY3));
		contentStream.showText(candidateIDansText);
		contentStream.endText();

		String assessmentDateText = "Assessment Date";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth4 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(assessmentDateText)
				/ 1000f * 40;
		float textX4 = 200;
		float textY4 = 460;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX4, textY4));
		contentStream.showText(assessmentDateText);
		contentStream.endText();

		String assessmentdateansText = "July 16,2024";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth5 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(assessmentdateansText)
				/ 1000f * 40;
		float textX5 = 400;
		float textY5 = 460;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX5, textY5));
		contentStream.showText(assessmentdateansText);
		contentStream.endText();

		String TotalcanText = "Total Candidates";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth6 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(TotalcanText) / 1000f
				* 40;
		float textX6 = 200;
		float textY6 = 430;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX6, textY6));
		contentStream.showText(TotalcanText);
		contentStream.endText();

		String RawscoreText = "My Raw Score";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth7 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(RawscoreText) / 1000f
				* 40;
		float textX7 = 200;
		float textY7 = 390;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX7, textY7));
		contentStream.showText(RawscoreText);
		contentStream.endText();

		String imagePath = "C:\\Users\\kusha\\OneDrive\\Desktop\\workspace\\pdfbuilder\\Picture1.jpg";
		PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath, document);
		float imageWidth = pdImage.getWidth();
		float imageHeight = pdImage.getHeight();
		float pageWidth = page.getMediaBox().getWidth();
		float pageHeight = page.getMediaBox().getHeight();
		float padding = 20;
		float x = padding;
		float y = 30;
		float scaledWidth = pageWidth - 2 * padding;
		float scaledHeight = imageHeight * (scaledWidth / imageWidth);
		contentStream.drawImage(pdImage, x, y, scaledWidth, scaledHeight);

		String text = "Demo Institution | Institución de demostración";
		float fontSize = 17;
		float textWidthinst = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD).getStringWidth(text) / 1000
				* fontSize;
		float textXinst = 40;
		float textYinst = y + scaledHeight - 200;

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 17);
		contentStream.setNonStrokingColor(1f, 1f, 1f);
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textXinst, textYinst));
		contentStream.showText(text);
		contentStream.endText();

		String imagePath2 = "C:\\Users\\kusha\\OneDrive\\Desktop\\workspace\\pdfbuilder\\Picture2.png";
		PDImageXObject pdImage2 = PDImageXObject.createFromFile(imagePath2, document);
		float imageWidth2 = pdImage2.getWidth();
		float imageHeight2 = pdImage2.getHeight();
		float pageWidth2 = page.getMediaBox().getWidth();
		float pageHeight2 = page.getMediaBox().getHeight();
		float padding2 = 20;
		float x2 = padding2;
		float y2 = 694;
		float scaledWidth2 = pageWidth2 - 2 * padding2;
		float scaledHeight2 = imageHeight2 * (scaledWidth2 / imageWidth2);
		contentStream.drawImage(pdImage2, x2, y2, scaledWidth2, scaledHeight2);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 23);
		contentStream.setNonStrokingColor(1f, 1f, 1f);
		float textXStudentName = x2 + 420;
		float textYStudentName = y2 + 20;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textXStudentName, textYStudentName));
		contentStream.showText(imageName);
		contentStream.endText();

	}

	/*
	 * 
	 * 
	 * Section 2
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	@SuppressWarnings("unused")
	private static void addContentToPage1Section2(PDDocument document, PDPage page, PDPageContentStream contentStream,
			String imageName, int totalStudents, String scoredMarks, String maxMarks, String percentage,
			int physicsMarks, int totalPhysicsScore, String physicspercentage, int mathsMarks, int totalmathsScore,
			String mathspercentage, String fileNameWithoutExtension,String FirstSubject,String SecondSubject) throws IOException {

		float[] textColor1 = hexToRGB("#F5C300");

		String scoreReportBelowText = fileNameWithoutExtension;
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 26);
		contentStream.setNonStrokingColor(textColor1[0], textColor1[1], textColor1[2]);
		float textWidth1 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(scoreReportBelowText)
				/ 1000f * 40;
		float textX1 = 70;
		float textY1 = 565;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX1, textY1));
		contentStream.showText(scoreReportBelowText);
		contentStream.endText();

		float[] textColor = hexToRGB("#6978aa");

		String scoreReportText = "Score Report";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 46);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(scoreReportText) / 1000f
				* 40;
		float textX = 70;
		float textY = 600;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX, textY));
		contentStream.showText(scoreReportText);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 46);
		contentStream.setNonStrokingColor(textColor[0], textColor[1], textColor[2]);
		float shadowOffsetX = 1;
		float shadowOffsetY = 1;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX + shadowOffsetX, textY - shadowOffsetY));
		contentStream.showText(scoreReportText);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 430);
		contentStream.showText(Integer.toString(totalStudents));
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 390);
		contentStream.showText(scoredMarks + "/" + maxMarks + "(" + percentage + ")");
		contentStream.endText();

		/*
		 * contentStream.beginText(); contentStream.setFont(new
		 * PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		 * contentStream.setNonStrokingColor(0f, 0f, 0f);
		 * contentStream.newLineAtOffset(425, 390); contentStream.showText(maxMarks);
		 * contentStream.endText();
		 */
		/*
		 * contentStream.beginText(); contentStream.setFont(new
		 * PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		 * contentStream.setNonStrokingColor(0f, 0f, 0f);
		 * contentStream.newLineAtOffset(443, 390);
		 * contentStream.showText("("+percentage+")"); contentStream.endText();
		 */
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(200, 355);
		contentStream.showText(FirstSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 355);
		contentStream
				.showText(physicsMarks + "/" + Integer.toString(totalPhysicsScore) + "(" + physicspercentage + ")");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(200, 325);
		contentStream.showText(SecondSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 325);
		contentStream.showText(mathsMarks + "/" + Integer.toString(totalmathsScore) + "(" + mathspercentage + ")");
		contentStream.endText();

		String candidateIDText = "Candidate ID";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth2 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(candidateIDText) / 1000f
				* 40;
		float textX2 = 200;
		float textY2 = 490;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX2, textY2));
		contentStream.showText(candidateIDText);
		contentStream.endText();

		String candidateIDansText = "5000041";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth3 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(candidateIDansText)
				/ 1000f * 40;
		float textX3 = 400;
		float textY3 = 490;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX3, textY3));
		contentStream.showText(candidateIDansText);
		contentStream.endText();

		String assessmentDateText = "Assessment Date";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth4 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(assessmentDateText)
				/ 1000f * 40;
		float textX4 = 200;
		float textY4 = 460;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX4, textY4));
		contentStream.showText(assessmentDateText);
		contentStream.endText();

		String assessmentdateansText = "July 16,2024";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth5 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(assessmentdateansText)
				/ 1000f * 40;
		float textX5 = 400;
		float textY5 = 460;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX5, textY5));
		contentStream.showText(assessmentdateansText);
		contentStream.endText();

		String TotalcanText = "Total Candidates";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth6 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(TotalcanText) / 1000f
				* 40;
		float textX6 = 200;
		float textY6 = 430;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX6, textY6));
		contentStream.showText(TotalcanText);
		contentStream.endText();

		String RawscoreText = "My Raw Score";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth7 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(RawscoreText) / 1000f
				* 40;
		float textX7 = 200;
		float textY7 = 390;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX7, textY7));
		contentStream.showText(RawscoreText);
		contentStream.endText();

		String imagePath = "C:\\Users\\kusha\\OneDrive\\Desktop\\workspace\\pdfbuilder\\Picture1.jpg";
		PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath, document);
		float imageWidth = pdImage.getWidth();
		float imageHeight = pdImage.getHeight();
		float pageWidth = page.getMediaBox().getWidth();
		float pageHeight = page.getMediaBox().getHeight();
		float padding = 20;
		float x = padding;
		float y = 30;
		float scaledWidth = pageWidth - 2 * padding;
		float scaledHeight = imageHeight * (scaledWidth / imageWidth);
		contentStream.drawImage(pdImage, x, y, scaledWidth, scaledHeight);

		String text = "Demo Institution | Institución de demostración";
		float fontSize = 17;
		float textWidthinst = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD).getStringWidth(text) / 1000
				* fontSize;
		float textXinst = 40;
		float textYinst = y + scaledHeight - 200;

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 17);
		contentStream.setNonStrokingColor(1f, 1f, 1f);
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textXinst, textYinst));
		contentStream.showText(text);
		contentStream.endText();

		String imagePath2 = "C:\\Users\\kusha\\OneDrive\\Desktop\\workspace\\pdfbuilder\\Picture2.png";
		PDImageXObject pdImage2 = PDImageXObject.createFromFile(imagePath2, document);
		float imageWidth2 = pdImage2.getWidth();
		float imageHeight2 = pdImage2.getHeight();
		float pageWidth2 = page.getMediaBox().getWidth();
		float pageHeight2 = page.getMediaBox().getHeight();
		float padding2 = 20;
		float x2 = padding2;
		float y2 = 694;
		float scaledWidth2 = pageWidth2 - 2 * padding2;
		float scaledHeight2 = imageHeight2 * (scaledWidth2 / imageWidth2);
		contentStream.drawImage(pdImage2, x2, y2, scaledWidth2, scaledHeight2);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 23);
		contentStream.setNonStrokingColor(1f, 1f, 1f);
		float textXStudentName = x2 + 420;
		float textYStudentName = y2 + 20;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textXStudentName, textYStudentName));
		contentStream.showText(imageName);
		contentStream.endText();

	}

	private static void addContentToPage2(PDPage page2, PDPageContentStream contentStream, String scoredMarks,
			PDDocument document, String maxMarks, double averageScoredMarks, List<StudentScore> top5Students,
			String correct, String incorrect, String unattempted, String phyScoredmarks, int averagePhyScoredMarks,
			List<StudentPhyScore> top5phyStudents, String chemScoredmarks, int PhyMaxMarks,
			List<StudentChemScore> top5chemStudents, int averageChemScoredMarks,
			List<StudentMathScore> top5mathStudents, String mathsScoredmarks, int averageMathScoredMarks,String FirstSubject,String SecondSubject,String ThirdSubject)
			throws IOException {

		contentStream.setNonStrokingColor(Color.YELLOW);

		float x = 20;
		float y = 750;
		float width = 570;
		float height = 20;

		contentStream.addRect(x, y, width, height);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x + 5, y + 5);
		contentStream.showText("Analysis of Overall Test");
		contentStream.endText();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));
		float x1 = 20;
		float y1 = 727;
		float width1 = 570;
		float height1 = 20;

		contentStream.addRect(x1, y1, width1, height1);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x1 + 5, y1 + 5);
		contentStream.showText("Comparison of Test Score");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 8);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(460, 715);
		contentStream.showText("My Correct, Incorrect &");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 8);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(448, 705);
		contentStream.showText("Unattempted Questions Count");
		contentStream.endText();

		addBarChart(contentStream, scoredMarks, document, maxMarks, averageScoredMarks, top5Students);
		addPieChart(contentStream, Integer.parseInt(correct), Integer.parseInt(incorrect),
				Integer.parseInt(unattempted), document);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(460, 550);
		contentStream.showText("Total Questions : " + maxMarks);
		contentStream.endText();

		int Attempt = Integer.parseInt(maxMarks) - Integer.parseInt(unattempted);
		float Attemptpercent = (Attempt / Integer.parseInt(maxMarks)) * 100;

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(469, 540);
		contentStream.showText("Attempt : " + Attemptpercent + " %");
		contentStream.endText();

		int Correct = Integer.parseInt(correct);
		int Max = Integer.parseInt(maxMarks);

		float temp = (Correct * 100) / Max;

		int Incorrect = Integer.parseInt(incorrect);
		float IncorrectPercent = (Incorrect * 100) / Max;

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(400, 515);
		contentStream.showText("Correct Ans : " + temp + "%" + " / " + "Incorrect Ans : " + IncorrectPercent + "%");
		contentStream.endText();

		float x2 = 40;
		float y2 = 510;
		float width2 = 355;
		float height2 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x2, y2, width2, height2);
		contentStream.stroke();

		float innerX = x2 + 5;
		float innerY = y2 + 3;
		float innerWidth = 10;
		float innerHeight = height2 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerX, innerY, innerWidth, innerHeight);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX + 14, innerY + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerX1 = x2 + 60;
		float innerY1 = y2 + 3;
		float innerWidth1 = 10;
		float innerHeight1 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerX1, innerY1, innerWidth1, innerHeight1);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX1 + 14, innerY1 + 1);
		contentStream.showText("Avg Score");
		contentStream.endText();

		float innerX2 = x2 + 120;
		float innerY2 = y2 + 3;
		float innerWidth2 = 10;
		float innerHeight2 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerX2, innerY2, innerWidth2, innerHeight2);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX2 + 14, innerY2 + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerX3 = x2 + 165;
		float innerY3 = y2 + 3;
		float innerWidth3 = 10;
		float innerHeight3 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerX3, innerY3, innerWidth3, innerHeight3);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX3 + 14, innerY3 + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerX4 = x2 + 210;
		float innerY4 = y2 + 3;
		float innerWidth4 = 10;
		float innerHeight4 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 255, 0));
		contentStream.addRect(innerX4, innerY4, innerWidth4, innerHeight4);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX4 + 14, innerY4 + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		float innerX5 = x2 + 260;
		float innerY5 = y2 + 3;
		float innerWidth5 = 10;
		float innerHeight5 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(160, 32, 240));
		contentStream.addRect(innerX5, innerY5, innerWidth5, innerHeight5);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX5 + 14, innerY5 + 1);
		contentStream.showText("Rank 4");
		contentStream.endText();

		float innerX6 = x2 + 310;
		float innerY6 = y2 + 3;
		float innerWidth6 = 10;
		float innerHeight6 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(173, 216, 230));
		contentStream.addRect(innerX6, innerY6, innerWidth6, innerHeight6);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX6 + 14, innerY6 + 1);
		contentStream.showText("Rank 5");
		contentStream.endText();

		float sectionX = 30;
		float sectionY = 495;

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(sectionX, sectionY);
		contentStream.showText("Test Toppers : ");

		StringBuilder topStudentsLine = new StringBuilder();
		for (int i = 0; i < Math.min(5, top5Students.size()); i++) {
			StudentScore student = top5Students.get(i);
			contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
			topStudentsLine.append(student.getName()).append(" (Score: ").append(student.getScore()).append(")");
			if (i < Math.min(5, top5Students.size()) - 1) {
				topStudentsLine.append(", ");
			}
		}
		contentStream.showText(topStudentsLine.toString());

		contentStream.endText();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));
		float x3 = 20;
		float y3 = 470;
		float width3 = 570;
		float height3 = 20;

		contentStream.addRect(x3, y3, width3, height3);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 5, y3 + 5);
		contentStream.showText("Comparison of Section wise Score");
		contentStream.endText();

		addBarChart2(contentStream, phyScoredmarks, document, maxMarks, averagePhyScoredMarks, top5phyStudents,
				mathsScoredmarks, PhyMaxMarks, top5chemStudents, Incorrect, top5mathStudents, mathsScoredmarks,
				averageMathScoredMarks);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(150, 270);
		contentStream.showText(FirstSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(300, 270);
		contentStream.showText(SecondSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(450, 270);
		contentStream.showText(ThirdSubject);
		contentStream.endText();

		float x4 = 120;
		float y4 = 250;
		float width4 = 355;
		float height4 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x4, y4, width4, height4);
		contentStream.stroke();

		float innerX7 = x4 + 5;
		float innerY7 = y4 + 3;
		float innerWidth7 = 10;
		float innerHeight7 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerX7, innerY7, innerWidth7, innerHeight7);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX7 + 14, innerY7 + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerX8 = x4 + 60;
		float innerY8 = y4 + 3;
		float innerWidth8 = 10;
		float innerHeight8 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerX8, innerY8, innerWidth8, innerHeight8);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX8 + 14, innerY8 + 1);
		contentStream.showText("Avg Score");
		contentStream.endText();

		float innerX9 = x4 + 120;
		float innerY9 = y4 + 3;
		float innerWidth9 = 10;
		float innerHeight9 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerX9, innerY9, innerWidth9, innerHeight9);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX9 + 14, innerY9 + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerX10 = x4 + 165;
		float innerY10 = y4 + 3;
		float innerWidth10 = 10;
		float innerHeight10 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerX10, innerY10, innerWidth10, innerHeight10);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX10 + 14, innerY10 + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerX11 = x4 + 210;
		float innerY11 = y4 + 3;
		float innerWidth11 = 10;
		float innerHeight11 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 255, 0));
		contentStream.addRect(innerX11, innerY11, innerWidth11, innerHeight11);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX11 + 14, innerY11 + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		float innerX12 = x4 + 260;
		float innerY12 = y4 + 3;
		float innerWidth12 = 10;
		float innerHeight12 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(162, 32, 240));
		contentStream.addRect(innerX12, innerY12, innerWidth12, innerHeight12);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX12 + 14, innerY12 + 1);
		contentStream.showText("Rank 4");
		contentStream.endText();

		float innerX13 = x4 + 310;
		float innerY13 = y4 + 3;
		float innerWidth13 = 10;
		float innerHeight13 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(179, 216, 230));
		contentStream.addRect(innerX13, innerY13, innerWidth13, innerHeight13);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX13 + 14, innerY13 + 1);
		contentStream.showText("Rank 5");
		contentStream.endText();
		int initX = 50;
		int initY = 230;
		int cellHeight = 20;
		int cellWidth = 65;

		int colCount = 8;
		int rowCount = 4;
		int m1 = top5mathStudents.get(0).getScore();
		int m2 = top5mathStudents.get(1).getScore();
		int m3 = top5mathStudents.get(2).getScore();
		int m4 = top5mathStudents.get(3).getScore();
		int m5 = top5mathStudents.get(4).getScore();

		int p1 = top5phyStudents.get(0).getScore();
		int p2 = top5phyStudents.get(1).getScore();
		int p3 = top5phyStudents.get(2).getScore();
		int p4 = top5phyStudents.get(3).getScore();
		int p5 = top5phyStudents.get(4).getScore();

		int c1 = top5chemStudents.get(0).getScore();
		int c2 = top5chemStudents.get(1).getScore();
		int c3 = top5chemStudents.get(2).getScore();
		int c4 = top5chemStudents.get(3).getScore();
		int c5 = top5chemStudents.get(4).getScore();

		String[][] tableData = { { "Subject", "My Score", "Average", "Rank 1", "Rank 2", "Rank 3", "Rank 4", "Rank 5" },
				{ ThirdSubject, mathsScoredmarks, Float.toString((float) averageMathScoredMarks), Integer.toString(m1),
						Integer.toString(m2), Integer.toString(m3), Integer.toString(m4), Integer.toString(m5) },
				{ FirstSubject, phyScoredmarks, Float.toString((float) averagePhyScoredMarks), Integer.toString(p1),
						Integer.toString(p2), Integer.toString(p3), Integer.toString(p4), Integer.toString(p5) },
				{ SecondSubject, chemScoredmarks, Float.toString((float) averageChemScoredMarks), Integer.toString(c1),
						Integer.toString(c2), Integer.toString(c3), Integer.toString(c4), Integer.toString(c5) },

		};

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				String content = tableData[i][j];

				PDType1Font font = (i == 0) ? new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
						: new PDType1Font(Standard14Fonts.FontName.HELVETICA);

				contentStream.addRect(initX, initY, cellWidth, -cellHeight);

				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.newLineAtOffset(initX + 20, initY - cellHeight + 5);
				contentStream.showText(content);
				contentStream.endText();

				initX += cellWidth;
			}
			initX = 50;
			initY -= cellHeight;
		}

		contentStream.stroke();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));
		float xi = 20;
		float yi = 120;
		float widthi = 570;
		float heighti = 20;

		contentStream.addRect(xi, yi, widthi, heighti);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(xi + 5, yi + 5);
		contentStream.showText("Breakdown of Test Score");
		contentStream.endText();

	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Section 2 Page 2
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private static void addContentTopage3Section2(PDPage page2, PDPageContentStream contentStream, String scoredMarks,
			PDDocument document, String maxMarks, double averageScoredMarks, List<StudentScore> top5Students,
			String correct, String incorrect, String unattempted, String phyScoredmarks, int averagePhyScoredMarks,
			List<StudentPhyScore> top5phyStudents, int PhyMaxMarks, List<StudentMathScore> top5mathStudents,
			String mathsScoredmarks, int averageMathScoredMarks,String FirstSubject,String SecondSubject) throws IOException {

		contentStream.setNonStrokingColor(Color.YELLOW);

		float x = 20;
		float y = 750;
		float width = 570;
		float height = 20;

		contentStream.addRect(x, y, width, height);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x + 5, y + 5);
		contentStream.showText("Analysis of Overall Test");
		contentStream.endText();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));
		float x1 = 20;
		float y1 = 727;
		float width1 = 570;
		float height1 = 20;

		contentStream.addRect(x1, y1, width1, height1);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x1 + 5, y1 + 5);
		contentStream.showText("Comparison of Test Score");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 8);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(460, 715);
		contentStream.showText("My Correct, Incorrect &");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 8);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(448, 705);
		contentStream.showText("Unattempted Questions Count");
		contentStream.endText();

		addBarChart(contentStream, scoredMarks, document, maxMarks, averageScoredMarks, top5Students);
		addPieChart(contentStream, Integer.parseInt(correct), Integer.parseInt(incorrect),
				Integer.parseInt(unattempted), document);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(460, 550);
		contentStream.showText("Total Questions : " + maxMarks);
		contentStream.endText();

		int Attempt = Integer.parseInt(maxMarks) - Integer.parseInt(unattempted);
		float Attemptpercent = (Attempt / Integer.parseInt(maxMarks)) * 100;

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(469, 540);
		contentStream.showText("Attempt : " + Attemptpercent + " %");
		contentStream.endText();

		int Correct = Integer.parseInt(correct);
		int Max = Integer.parseInt(maxMarks);

		float temp = (Correct * 100) / Max;

		int Incorrect = Integer.parseInt(incorrect);
		float IncorrectPercent = (Incorrect * 100) / Max;

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(400, 515);
		contentStream.showText("Correct Ans : " + temp + "%" + " / " + "Incorrect Ans : " + IncorrectPercent + "%");
		contentStream.endText();

		float x2 = 40;
		float y2 = 510;
		float width2 = 355;
		float height2 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x2, y2, width2, height2);
		contentStream.stroke();

		float innerX = x2 + 5;
		float innerY = y2 + 3;
		float innerWidth = 10;
		float innerHeight = height2 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerX, innerY, innerWidth, innerHeight);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX + 14, innerY + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerX1 = x2 + 60;
		float innerY1 = y2 + 3;
		float innerWidth1 = 10;
		float innerHeight1 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerX1, innerY1, innerWidth1, innerHeight1);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX1 + 14, innerY1 + 1);
		contentStream.showText("Avg Score");
		contentStream.endText();

		float innerX2 = x2 + 120;
		float innerY2 = y2 + 3;
		float innerWidth2 = 10;
		float innerHeight2 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerX2, innerY2, innerWidth2, innerHeight2);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX2 + 14, innerY2 + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerX3 = x2 + 165;
		float innerY3 = y2 + 3;
		float innerWidth3 = 10;
		float innerHeight3 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerX3, innerY3, innerWidth3, innerHeight3);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX3 + 14, innerY3 + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerX4 = x2 + 210;
		float innerY4 = y2 + 3;
		float innerWidth4 = 10;
		float innerHeight4 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 255, 0));
		contentStream.addRect(innerX4, innerY4, innerWidth4, innerHeight4);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX4 + 14, innerY4 + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		float innerX5 = x2 + 260;
		float innerY5 = y2 + 3;
		float innerWidth5 = 10;
		float innerHeight5 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(160, 32, 240));
		contentStream.addRect(innerX5, innerY5, innerWidth5, innerHeight5);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX5 + 14, innerY5 + 1);
		contentStream.showText("Rank 4");
		contentStream.endText();

		float innerX6 = x2 + 310;
		float innerY6 = y2 + 3;
		float innerWidth6 = 10;
		float innerHeight6 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(173, 216, 230));
		contentStream.addRect(innerX6, innerY6, innerWidth6, innerHeight6);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX6 + 14, innerY6 + 1);
		contentStream.showText("Rank 5");
		contentStream.endText();

		float sectionX = 30;
		float sectionY = 495;

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(sectionX, sectionY);
		contentStream.showText("Test Toppers : ");

		StringBuilder topStudentsLine = new StringBuilder();
		for (int i = 0; i < Math.min(5, top5Students.size()); i++) {
			StudentScore student = top5Students.get(i);
			contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
			topStudentsLine.append(student.getName()).append(" (Score: ").append(student.getScore()).append(")");
			if (i < Math.min(5, top5Students.size()) - 1) {
				topStudentsLine.append(", ");
			}
		}
		contentStream.showText(topStudentsLine.toString());

		contentStream.endText();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));
		float x3 = 20;
		float y3 = 470;
		float width3 = 570;
		float height3 = 20;

		contentStream.addRect(x3, y3, width3, height3);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 5, y3 + 5);
		contentStream.showText("Comparison of Section wise Score");
		contentStream.endText();

		addBarChart2Section2(contentStream, phyScoredmarks, document, maxMarks, averagePhyScoredMarks, top5phyStudents,
				PhyMaxMarks, top5mathStudents, mathsScoredmarks, averageMathScoredMarks);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(150, 270);
		contentStream.showText(FirstSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(450, 270);
		contentStream.showText(SecondSubject);
		contentStream.endText();

		float x4 = 120;
		float y4 = 250;
		float width4 = 355;
		float height4 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x4, y4, width4, height4);
		contentStream.stroke();

		float innerX7 = x4 + 5;
		float innerY7 = y4 + 3;
		float innerWidth7 = 10;
		float innerHeight7 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerX7, innerY7, innerWidth7, innerHeight7);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX7 + 14, innerY7 + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerX8 = x4 + 60;
		float innerY8 = y4 + 3;
		float innerWidth8 = 10;
		float innerHeight8 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerX8, innerY8, innerWidth8, innerHeight8);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX8 + 14, innerY8 + 1);
		contentStream.showText("Avg Score");
		contentStream.endText();

		float innerX9 = x4 + 120;
		float innerY9 = y4 + 3;
		float innerWidth9 = 10;
		float innerHeight9 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerX9, innerY9, innerWidth9, innerHeight9);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX9 + 14, innerY9 + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerX10 = x4 + 165;
		float innerY10 = y4 + 3;
		float innerWidth10 = 10;
		float innerHeight10 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerX10, innerY10, innerWidth10, innerHeight10);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX10 + 14, innerY10 + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerX11 = x4 + 210;
		float innerY11 = y4 + 3;
		float innerWidth11 = 10;
		float innerHeight11 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 255, 0));
		contentStream.addRect(innerX11, innerY11, innerWidth11, innerHeight11);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX11 + 14, innerY11 + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		float innerX12 = x4 + 260;
		float innerY12 = y4 + 3;
		float innerWidth12 = 10;
		float innerHeight12 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(162, 32, 240));
		contentStream.addRect(innerX12, innerY12, innerWidth12, innerHeight12);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX12 + 14, innerY12 + 1);
		contentStream.showText("Rank 4");
		contentStream.endText();

		float innerX13 = x4 + 310;
		float innerY13 = y4 + 3;
		float innerWidth13 = 10;
		float innerHeight13 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(179, 216, 230));
		contentStream.addRect(innerX13, innerY13, innerWidth13, innerHeight13);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX13 + 14, innerY13 + 1);
		contentStream.showText("Rank 5");
		contentStream.endText();
		int initX = 50;
		int initY = 230;
		int cellHeight = 20;
		int cellWidth = 65;

		int colCount = 8;
		int rowCount = 3;
		int m1 = top5mathStudents.get(0).getScore();
		int m2 = top5mathStudents.get(1).getScore();
		int m3 = top5mathStudents.get(2).getScore();
		int m4 = top5mathStudents.get(3).getScore();
		int m5 = top5mathStudents.get(4).getScore();

		int p1 = top5phyStudents.get(0).getScore();
		int p2 = top5phyStudents.get(1).getScore();
		int p3 = top5phyStudents.get(2).getScore();
		int p4 = top5phyStudents.get(3).getScore();
		int p5 = top5phyStudents.get(4).getScore();

		String[][] tableData = { { "Subject", "My Score", "Average", "Rank 1", "Rank 2", "Rank 3", "Rank 4", "Rank 5" },
				
				{ FirstSubject, phyScoredmarks, Float.toString((float) averagePhyScoredMarks), Integer.toString(p1),
						Integer.toString(p2), Integer.toString(p3), Integer.toString(p4), Integer.toString(p5) },
				{ SecondSubject, mathsScoredmarks, Float.toString((float) averageMathScoredMarks), Integer.toString(m1),
							Integer.toString(m2), Integer.toString(m3), Integer.toString(m4), Integer.toString(m5) },

		};

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				String content = tableData[i][j];

				PDType1Font font = (i == 0) ? new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
						: new PDType1Font(Standard14Fonts.FontName.HELVETICA);

				contentStream.addRect(initX, initY, cellWidth, -cellHeight);

				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.newLineAtOffset(initX + 20, initY - cellHeight + 5);
				contentStream.showText(content);
				contentStream.endText();

				initX += cellWidth;
			}
			initX = 50;
			initY -= cellHeight;
		}

		contentStream.stroke();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));
		float xi = 20;
		float yi = 120;
		float widthi = 570;
		float heighti = 20;

		contentStream.addRect(xi, yi, widthi, heighti);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(xi + 5, yi + 5);
		contentStream.showText("Breakdown of Test Score");
		contentStream.endText();

	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * section 3 page 3
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private static void addContentTopage3(String imageName, PDPage page3, PDPageContentStream contentStream,
			PDDocument document, int totalPhysicsScore, int totalchemistryScore, int totalmathsScore, int physicsMarks,
			int chemistryMarks, int mathsMarks, int attemptmathQuestions, int mathsScore1, int mathsScore2,
			String mathspercentage, int attemptphysicsQuestions, int physicsScore1, int physicsScore2,
			String physicspercentage, int attemptchemistryQuestions, int chemistryScore1, int chemistryScore2,
			String chemistrypercentage, List<StudentMathScore> studentmathScores,
			List<StudentPhyScore> studentphyScores, List<StudentChemScore> studentchemScores, int physicsScore3,
			int chemScore3, int mathsScore3, List<StudentPositiveScore> top5Positive, String PositiveScore,
			List<StudentNegativeScore> top5Negative, String NegativeScore,String FirstSubject,String SecondSubject,String ThirdSubject) throws IOException {

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(80, 750);
		contentStream.showText("Count of Questions");
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(270, 750);
		contentStream.showText("Marks of Questions");
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(480, 750);
		contentStream.showText("My Score");
		contentStream.endText();

		addPieChart2(document, contentStream, totalPhysicsScore, totalchemistryScore, totalmathsScore,FirstSubject,SecondSubject,ThirdSubject);
		addPieChart3(document, contentStream, totalPhysicsScore, totalchemistryScore, totalmathsScore,FirstSubject,SecondSubject,ThirdSubject);
		if (physicsMarks < 0 && chemistryMarks < 0 && mathsMarks < 0) {

			float x = 450;
			float y = 680;
			float width = 100;
			float height = 20;

			contentStream.addRect(x, y, width, height);
			contentStream.setNonStrokingColor(new Color(255, 127, 127));
			contentStream.fill();

			contentStream.setStrokingColor(Color.BLACK);
			contentStream.addRect(x, y, width, height);
			contentStream.stroke();

			contentStream.beginText();
			contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
			contentStream.setNonStrokingColor(Color.BLACK);
			contentStream.newLineAtOffset(x + 10, y + 5);
			contentStream.showText("Negative Score");
			contentStream.endText();
		} else {
			addPieChart4(document, contentStream, physicsMarks, chemistryMarks, mathsMarks,FirstSubject,SecondSubject,ThirdSubject);

		}
		int initX = 50;
		int initY = 600;
		int cellHeight = 20;
		int cellWidth = 60;

		int colCount = 9;
		int rowCount = 4;

		String targetImageName = imageName;
		int mathrank = getStudentRank(studentmathScores, targetImageName);
		int phyrank = getphyStudentRank(studentphyScores, targetImageName);
		int chemrank = getchemtudentRank(studentchemScores, targetImageName);

		String[][] tableData = {
				{ "Subject", "Total Q.", "Attempt Q.", "Correct", "Incorrect", "Raw Score", "Total(Max)", "Percent",
						"Rank" },
				{ ThirdSubject, Integer.toString(totalmathsScore), Integer.toString(attemptmathQuestions),
						Integer.toString(mathsScore1), Integer.toString(mathsScore2), Integer.toString(mathsMarks),
						Integer.toString(totalmathsScore), mathspercentage, Integer.toString(mathrank) },
				{ FirstSubject, Integer.toString(totalPhysicsScore), Integer.toString(attemptphysicsQuestions),
						Integer.toString(physicsScore1), Integer.toString(physicsScore2),
						Integer.toString(physicsMarks), Integer.toString(totalPhysicsScore), physicspercentage,
						Integer.toString(phyrank) },
				{ ThirdSubject, Integer.toString(totalchemistryScore), Integer.toString(attemptchemistryQuestions),
						Integer.toString(chemistryScore1), Integer.toString(chemistryScore2),
						Integer.toString(chemistryMarks), Integer.toString(totalchemistryScore), chemistrypercentage,
						Integer.toString(chemrank) },

		};

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				String content = tableData[i][j];

				PDType1Font font = (i == 0) ? new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
						: new PDType1Font(Standard14Fonts.FontName.HELVETICA);

				contentStream.addRect(initX, initY, cellWidth, -cellHeight);

				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.newLineAtOffset(initX + 10, initY - cellHeight + 5);
				contentStream.showText(content);
				contentStream.endText();

				initX += cellWidth;
			}
			initX = 50;
			initY -= cellHeight;
		}

		contentStream.stroke();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));

		float x = 20;
		float y = 495;
		float width = 570;
		float height = 20;

		contentStream.addRect(x, y, width, height);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x + 5, y + 5);
		contentStream.showText("Analysis of Overall Test");
		contentStream.endText();

		addBarChart31(contentStream, totalPhysicsScore, totalchemistryScore, totalmathsScore, document, physicsScore1,
				physicsScore2, physicsScore3, chemistryScore1, chemistryScore2, chemScore3, mathsScore1, mathsScore2,
				mathsScore3);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(140, 270);
		contentStream.showText(FirstSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(300, 270);
		contentStream.showText(SecondSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(480, 270);
		contentStream.showText(ThirdSubject);
		contentStream.endText();

		float x2 = 140;
		float y2 = 240;
		float width2 = 355;
		float height2 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x2, y2, width2, height2);
		contentStream.stroke();

		float innerX = x2 + 30;
		float innerY = y2 + 3;
		float innerWidth = 10;
		float innerHeight = height2 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerX, innerY, innerWidth, innerHeight);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX + 14, innerY + 1);
		contentStream.showText("Total Questions");
		contentStream.endText();

		float innerX1 = x2 + 110;
		float innerY1 = y2 + 3;
		float innerWidth1 = 10;
		float innerHeight1 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerX1, innerY1, innerWidth1, innerHeight1);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX1 + 14, innerY1 + 1);
		contentStream.showText("Correct Questions");
		contentStream.endText();

		float innerX2 = x2 + 200;
		float innerY2 = y2 + 3;
		float innerWidth2 = 10;
		float innerHeight2 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerX2, innerY2, innerWidth2, innerHeight2);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX2 + 14, innerY2 + 1);
		contentStream.showText("Incorrect Questions");
		contentStream.endText();

		float innerX3 = x2 + 300;
		float innerY3 = y2 + 3;
		float innerWidth3 = 10;
		float innerHeight3 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerX3, innerY3, innerWidth3, innerHeight3);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX3 + 14, innerY3 + 1);
		contentStream.showText("Skipped");
		contentStream.endText();
		contentStream.setNonStrokingColor(new Color(255, 255, 0));

		float x3 = 20;
		float y3 = 200;
		float width3 = 570;
		float height3 = 20;

		contentStream.addRect(x3, y3, width3, height3);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 5, y3 + 5);
		contentStream.showText("Comparison of Positive Score");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 300, y3 + 5);
		contentStream.showText("Comparison of Negative Score");
		contentStream.endText();

		addBarChart3(contentStream, totalPhysicsScore, document, top5Positive, PositiveScore);
		addBarChart4(contentStream, totalPhysicsScore, document, top5Negative, NegativeScore);

		float x4 = 100;
		float y4 = 40;
		float width4 = 355;
		float height4 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x4, y4, width4, height4);
		contentStream.stroke();

		float innerXa = x4 + 30;
		float innerYa = y4 + 3;
		float innerWidtha = 10;
		float innerHeighta = height4 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerXa, innerYa, innerWidtha, innerHeighta);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXa + 14, innerYa + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerXb = x4 + 85;
		float innerYb = y4 + 3;
		float innerWidthb = 10;
		float innerHeightb = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerXb, innerYb, innerWidthb, innerHeightb);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXb + 14, innerYb + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerXc = x4 + 130;
		float innerYc = y4 + 3;
		float innerWidthc = 10;
		float innerHeightc = height4 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerXc, innerYc, innerWidthc, innerHeightc);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXc + 14, innerYc + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerXd = x4 + 175;
		float innerYd = y4 + 3;
		float innerWidthd = 10;
		float innerHeightd = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerXd, innerYd, innerWidthd, innerHeightd);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXd + 14, innerYd + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		float innerX4 = x4 + 220;
		float innerY4 = y4 + 3;
		float innerWidth4 = 10;
		float innerHeight4 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 255, 0));
		contentStream.addRect(innerX4, innerY4, innerWidth4, innerHeight4);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX4 + 14, innerY4 + 1);
		contentStream.showText("Rank 4");
		contentStream.endText();

		float innerX5 = x4 + 270;
		float innerY5 = y4 + 3;
		float innerWidth5 = 10;
		float innerHeight5 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(162, 32, 240));
		contentStream.addRect(innerX5, innerY5, innerWidth5, innerHeight5);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX5 + 14, innerY5 + 1);
		contentStream.showText("Rank 5");
		contentStream.endText();

	}

	@SuppressWarnings("unused")
	private static void addContentTopage4(PDPageContentStream contentStream, PDDocument document, int physicsScore1,
			int chemistryScore1, int mathsScore1, int physicsScore2, int chemistryScore2, int mathsScore2,
			List<StudentPhysicsPositiveScore> top5PhysicsPositive, List<StudentChemPositiveScore> top5ChemPositive,
			List<StudentMathPositiveScore> top5MathPositive, List<StudentPhysicsNegativeScore> top5PhysicsNegative,
			List<StudentChemNegativeScore> top5ChemNegative, List<StudentMathNegativeScore> top5MathNegative,String FirstSubject,String SecondSubject,String ThirdSubject)
			throws IOException {
		contentStream.setNonStrokingColor(new Color(0, 149, 193));

		float x3 = 20;
		float y3 = 750;
		float width3 = 570;
		float height3 = 20;

		contentStream.addRect(x3, y3, width3, height3);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 5, y3 + 5);
		contentStream.showText("Distribution of Section wise Positive Score");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 300, y3 + 5);
		contentStream.showText("Distribution of Section wise Negative Score");
		contentStream.endText();

		addPieChart5(contentStream, document, physicsScore1, chemistryScore1, mathsScore1,FirstSubject,SecondSubject,ThirdSubject);
		addPieChart6(contentStream, document, physicsScore2, chemistryScore2, mathsScore2,FirstSubject,SecondSubject,ThirdSubject);

		contentStream.setNonStrokingColor(new Color(0, 149, 193));

		float x4 = 20;
		float y4 = 580;
		float width4 = 570;
		float height4 = 20;

		contentStream.addRect(x4, y4, width4, height4);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x4 + 5, y4 + 5);
		contentStream.showText("Comparision of Section wise Positive Score");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x4 + 300, y4 + 5);
		contentStream.showText("Comparision of Section wise Negative Score");
		contentStream.endText();

		addBarChart5(contentStream, document, physicsScore1, chemistryScore1, mathsScore1, top5PhysicsPositive,
				top5ChemPositive, top5MathPositive);
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(50, 432);
		contentStream.showText(FirstSubject);
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(140, 432);
		contentStream.showText(SecondSubject);
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(230, 432);
		contentStream.showText(ThirdSubject);
		contentStream.endText();
		addBarChart6(contentStream, document, physicsScore2, chemistryScore2, mathsScore2, top5PhysicsNegative,
				top5ChemNegative, top5MathNegative);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(350, 432);
		contentStream.showText(FirstSubject);
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(430, 432);
		contentStream.showText(SecondSubject);
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(500, 432);
		contentStream.showText(ThirdSubject);
		contentStream.endText();

		float x41 = 120;
		float y41 = 400;
		float width41 = 355;
		float height41 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x41, y41, width41, height41);
		contentStream.stroke();

		float innerXa = x41 + 30;
		float innerYa = y41 + 3;
		float innerWidtha = 10;
		float innerHeighta = height41 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerXa, innerYa, innerWidtha, innerHeighta);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXa + 14, innerYa + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerXb = x41 + 85;
		float innerYb = y41 + 3;
		float innerWidthb = 10;
		float innerHeightb = height41 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerXb, innerYb, innerWidthb, innerHeightb);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXb + 14, innerYb + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerXc = x41 + 130;
		float innerYc = y41 + 3;
		float innerWidthc = 10;
		float innerHeightc = height41 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerXc, innerYc, innerWidthc, innerHeightc);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXc + 14, innerYc + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerXd = x41 + 180;
		float innerYd = y41 + 3;
		float innerWidthd = 10;
		float innerHeightd = height41 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerXd, innerYd, innerWidthd, innerHeightd);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXd + 14, innerYd + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		float innerX4 = x41 + 230;
		float innerY4 = y41 + 3;
		float innerWidth4 = 10;
		float innerHeight4 = height41 - 6;

		contentStream.setNonStrokingColor(new Color(255, 255, 0));
		contentStream.addRect(innerX4, innerY4, innerWidth4, innerHeight4);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX4 + 14, innerY4 + 1);
		contentStream.showText("Rank 4");
		contentStream.endText();

		float innerX5 = x41 + 280;
		float innerY5 = y41 + 3;
		float innerWidth5 = 10;
		float innerHeight5 = height41 - 6;

		contentStream.setNonStrokingColor(new Color(162, 32, 240));
		contentStream.addRect(innerX5, innerY5, innerWidth5, innerHeight5);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX5 + 14, innerY5 + 1);
		contentStream.showText("Rank 5");
		contentStream.endText();

		int initX = 50;
		int initY = 380;
		int cellHeight = 20;
		int cellWidth = 130;

		int colCount = 1;
		int rowCount = 5;

		String[][] tableData = { { "Subject" }, { "" }, { ThirdSubject }, { FirstSubject }, { SecondSubject }

		};

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				String content = tableData[i][j];

				PDType1Font font = (i == 0) ? new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
						: new PDType1Font(Standard14Fonts.FontName.HELVETICA);

				contentStream.addRect(initX, initY, cellWidth, -cellHeight);

				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.newLineAtOffset(initX + 40, initY - cellHeight + 5);
				contentStream.showText(content);
				contentStream.endText();

				initX += cellWidth;
			}
			initX = 50;
			initY -= cellHeight;
		}

		contentStream.stroke();
		int initX1 = 180;
		int initY1 = 380;
		int cellHeight1 = 20;
		int cellWidth1 = 30;

		int colCount1 = 6;
		int rowCount1 = 4;

		String[] headers = { "My Score", "Rank 1", "Rank 2", "Rank 3", "Rank 4", "Rank 5" };
		String[] subHeaders = { "+ve", "-ve", "+ve", "-ve", "+ve", "-ve", "+ve", "-ve", "+ve", "-ve", "+ve", "-ve" };

		int t1mp = top5MathPositive.get(0).getScore();
		int t2mp = top5MathPositive.get(1).getScore();
		int t3mp = top5MathPositive.get(2).getScore();
		int t4mp = top5MathPositive.get(3).getScore();
		int t5mp = top5MathPositive.get(4).getScore();

		int t1mn = top5MathNegative.get(0).getScore();
		int t2mn = top5MathNegative.get(1).getScore();
		int t3mn = top5MathNegative.get(2).getScore();
		int t4mn = top5MathNegative.get(3).getScore();
		int t5mn = top5MathNegative.get(4).getScore();

		int t1pp = top5PhysicsPositive.get(0).getScore();
		int t2pp = top5PhysicsPositive.get(1).getScore();
		int t3pp = top5PhysicsPositive.get(2).getScore();
		int t4pp = top5PhysicsPositive.get(3).getScore();
		int t5pp = top5PhysicsPositive.get(4).getScore();

		int t1pn = top5PhysicsNegative.get(0).getScore();
		int t2pn = top5PhysicsNegative.get(1).getScore();
		int t3pn = top5PhysicsNegative.get(2).getScore();
		int t4pn = top5PhysicsNegative.get(3).getScore();
		int t5pn = top5PhysicsNegative.get(4).getScore();

		int t1cp = top5ChemPositive.get(0).getScore();
		int t2cp = top5ChemPositive.get(1).getScore();
		int t3cp = top5ChemPositive.get(2).getScore();
		int t4cp = top5ChemPositive.get(3).getScore();
		int t5cp = top5ChemPositive.get(4).getScore();

		int t1cn = top5ChemNegative.get(0).getScore();
		int t2cn = top5ChemNegative.get(1).getScore();
		int t3cn = top5ChemNegative.get(2).getScore();
		int t4cn = top5ChemNegative.get(3).getScore();
		int t5cn = top5ChemNegative.get(4).getScore();

		String[][] tableData1 = {
				{ Integer.toString(mathsScore1), Integer.toString(mathsScore2), Integer.toString(t1mp),
						Integer.toString(t1mn), Integer.toString(t2mp), Integer.toString(t2mn), Integer.toString(t3mp),
						Integer.toString(t3mn), Integer.toString(t4mp), Integer.toString(t4mn), Integer.toString(t5mp),
						Integer.toString(t5mn) },
				{ Integer.toString(physicsScore1), Integer.toString(physicsScore2), Integer.toString(t1pp),
						Integer.toString(t1pn), Integer.toString(t2pp), Integer.toString(t2pn), Integer.toString(t3pp),
						Integer.toString(t3pn), Integer.toString(t4pp), Integer.toString(t4pn), Integer.toString(t5pp),
						Integer.toString(t5pn) },
				{ Integer.toString(chemistryScore1), Integer.toString(chemistryScore2), Integer.toString(t1cp),
						Integer.toString(t1cn), Integer.toString(t2cp), Integer.toString(t2cn), Integer.toString(t3cp),
						Integer.toString(t3cn), Integer.toString(t4cp), Integer.toString(t4cn), Integer.toString(t5cp),
						Integer.toString(t5cn) } };

		for (int i = 0; i < headers.length; i++) {
			String content = headers[i];
			contentStream.addRect(initX1, initY1, cellWidth1 * 2, -cellHeight1);
			contentStream.beginText();
			contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 9);
			contentStream.setNonStrokingColor(Color.BLACK);
			contentStream.newLineAtOffset(initX1 + 10, initY1 - cellHeight1 + 5);
			contentStream.showText(content);
			contentStream.endText();
			initX1 += cellWidth1 * 2;
		}
		initX1 = 180;
		initY1 -= cellHeight1;

		for (int i = 0; i < subHeaders.length; i++) {
			String content = subHeaders[i];
			contentStream.addRect(initX1, initY1, cellWidth1, -cellHeight1);
			contentStream.beginText();
			contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 9);
			contentStream.setNonStrokingColor(Color.BLACK);
			contentStream.newLineAtOffset(initX1 + 10, initY1 - cellHeight1 + 5);
			contentStream.showText(content);
			contentStream.endText();
			initX1 += cellWidth1;
		}
		initX1 = 180;
		initY1 -= cellHeight1;

		for (int i = 0; i < tableData1.length; i++) {
			for (int j = 0; j < tableData1[i].length; j++) {
				String content = tableData1[i][j];
				contentStream.addRect(initX1, initY1, cellWidth1, -cellHeight1);
				contentStream.beginText();
				contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.newLineAtOffset(initX1 + 10, initY1 - cellHeight1 + 5);
				contentStream.showText(content);
				contentStream.endText();
				initX1 += cellWidth1;
			}
			initX1 = 180;
			initY1 -= cellHeight1;
		}

		contentStream.stroke();

	}

	private static void addBarChart6(PDPageContentStream contentStream, PDDocument document, int physicsScore2,
			int chemistryScore2, int mathsScore2, List<StudentPhysicsNegativeScore> top5PhysicsNegative,
			List<StudentChemNegativeScore> top5ChemNegative, List<StudentMathNegativeScore> top5MathNegative)
			throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue((physicsScore2), "phy positive Marks", "");
		int topCount = 1;
		for (StudentPhysicsNegativeScore studentScore : top5PhysicsNegative) {
			dataset.addValue(studentScore.getScore(), "phy Top Marks " + topCount++, "");
		}
		dataset.addValue((chemistryScore2), "chem positive Marks", "");
		int topCount0 = 1;
		for (StudentChemNegativeScore studentScore : top5ChemNegative) {
			dataset.addValue(studentScore.getScore(), "chem Top Marks " + topCount0++, "");
		}
		dataset.addValue((mathsScore2), "math positive Marks", "");
		int topCount1 = 1;
		for (StudentMathNegativeScore studentScore : top5MathNegative) {
			dataset.addValue(studentScore.getScore(), "math Top Marks " + topCount1++, "");
		}

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));

		BarRenderer renderer = (BarRenderer) plot.getRenderer();

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(255, 255, 0));
		renderer.setSeriesPaint(5, new Color(162, 32, 240));

		renderer.setSeriesPaint(6, new Color(0, 0, 255));
		renderer.setSeriesPaint(7, new Color(255, 0, 0));

		renderer.setSeriesPaint(8, new Color(0, 255, 0));
		renderer.setSeriesPaint(9, new Color(255, 165, 0));
		renderer.setSeriesPaint(10, new Color(255, 255, 0));
		renderer.setSeriesPaint(11, new Color(162, 32, 240));

		renderer.setSeriesPaint(12, new Color(0, 0, 255));
		renderer.setSeriesPaint(13, new Color(255, 0, 0));

		renderer.setSeriesPaint(14, new Color(0, 255, 0));
		renderer.setSeriesPaint(15, new Color(255, 165, 0));
		renderer.setSeriesPaint(16, new Color(255, 255, 0));
		renderer.setSeriesPaint(17, new Color(162, 32, 240));
		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 10));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(0, 12);
		yAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 7));
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		Font tickLabelFont = new Font("Arial", Font.PLAIN, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(1);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 10));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 300, 430, 270, 145);
	}

	private static void addBarChart5(PDPageContentStream contentStream, PDDocument document, int physicsScore1,
			int chemistryScore1, int mathsScore1, List<StudentPhysicsPositiveScore> top5PhysicsPositive,
			List<StudentChemPositiveScore> top5ChemPositive, List<StudentMathPositiveScore> top5MathPositive)
			throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue((physicsScore1), "phy positive Marks", "");
		int topCount = 1;
		for (StudentPhysicsPositiveScore studentScore : top5PhysicsPositive) {
			dataset.addValue(studentScore.getScore(), "phy Top Marks " + topCount++, "");
		}
		dataset.addValue((chemistryScore1), "chem positive Marks", "");
		int topCount0 = 1;
		for (StudentChemPositiveScore studentScore : top5ChemPositive) {
			dataset.addValue(studentScore.getScore(), "chem Top Marks " + topCount0++, "");
		}
		dataset.addValue((mathsScore1), "math positive Marks", "");
		int topCount1 = 1;
		for (StudentMathPositiveScore studentScore : top5MathPositive) {
			dataset.addValue(studentScore.getScore(), "math Top Marks " + topCount1++, "");
		}

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));

		BarRenderer renderer = (BarRenderer) plot.getRenderer();

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(255, 255, 0));
		renderer.setSeriesPaint(5, new Color(162, 32, 240));

		renderer.setSeriesPaint(6, new Color(0, 0, 255));
		renderer.setSeriesPaint(7, new Color(255, 0, 0));

		renderer.setSeriesPaint(8, new Color(0, 255, 0));
		renderer.setSeriesPaint(9, new Color(255, 165, 0));
		renderer.setSeriesPaint(10, new Color(255, 255, 0));
		renderer.setSeriesPaint(11, new Color(162, 32, 240));

		renderer.setSeriesPaint(12, new Color(0, 0, 255));
		renderer.setSeriesPaint(13, new Color(255, 0, 0));

		renderer.setSeriesPaint(14, new Color(0, 255, 0));
		renderer.setSeriesPaint(15, new Color(255, 165, 0));
		renderer.setSeriesPaint(16, new Color(255, 255, 0));
		renderer.setSeriesPaint(17, new Color(162, 32, 240));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 10));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(0, 12);
		yAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 7));
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		Font tickLabelFont = new Font("Arial", Font.PLAIN, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(1);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 10));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 15, 430, 270, 145);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart6(PDPageContentStream contentStream, PDDocument document, int physicsScore2,
			int chemistryScore2, int mathsScore2,String FirstSubject,String SecondSubject,String ThirdSubject) throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, (physicsScore2));
		dataset.setValue(SecondSubject, (chemistryScore2));
		dataset.setValue(ThirdSubject, (mathsScore2));

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));
		plot.setSectionPaint(SecondSubject, new Color(255, 0, 0));
		plot.setSectionPaint(ThirdSubject, new Color(0, 255, 0));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart1.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 350, 600, 230, 140);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart5(PDPageContentStream contentStream, PDDocument document, int physicsScore1,
			int chemistryScore1, int mathsScore1,String FirstSubject,String SecondSubject,String ThirdSubject) throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, (physicsScore1));
		dataset.setValue(SecondSubject, (chemistryScore1));
		dataset.setValue(ThirdSubject, (mathsScore1));

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));
		plot.setSectionPaint(SecondSubject, new Color(255, 0, 0));
		plot.setSectionPaint(ThirdSubject, new Color(0, 255, 0));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 50, 600, 230, 140);
	}

	private static void addBarChart4(PDPageContentStream contentStream, int totalPhysicsScore, PDDocument document,
			List<StudentNegativeScore> top5Negative, String NegativeScore) throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(Integer.parseInt(NegativeScore), "Scored Marks", "");

		int topCount = 1;
		for (StudentNegativeScore studentScore : top5Negative) {
			dataset.addValue(studentScore.getScore(), "Top Marks " + topCount++, "");
		}

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));

		BarRenderer renderer = (BarRenderer) plot.getRenderer();

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(255, 255, 0));
		renderer.setSeriesPaint(5, new Color(162, 32, 240));
		renderer.setSeriesPaint(6, new Color(172, 216, 230));
		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 10));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(0, 24);
		yAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 7));
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		Font tickLabelFont = new Font("Arial", Font.PLAIN, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(1);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 10));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 300, 50, 270, 145);
	}

	private static void addBarChart3(PDPageContentStream contentStream, int totalPhysicsScore, PDDocument document,
			List<StudentPositiveScore> top5Positive, String positiveScore) throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(Integer.parseInt(positiveScore), "Scored Marks", "");

		int topCount = 1;
		for (StudentPositiveScore studentScore : top5Positive) {
			dataset.addValue(studentScore.getScore(), "Top Marks " + topCount++, "");
		}

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));

		BarRenderer renderer = (BarRenderer) plot.getRenderer();

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(255, 255, 0));
		renderer.setSeriesPaint(5, new Color(162, 32, 240));
		renderer.setSeriesPaint(6, new Color(172, 216, 230));
		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 10));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(0, 22);
		yAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 7));
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		Font tickLabelFont = new Font("Arial", Font.PLAIN, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(1);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 10));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 15, 50, 270, 145);
	}

	private static void addBarChart31(PDPageContentStream contentStream, int totalPhysicsScore, int totalChemistryScore,
			int totalmathsScore, PDDocument document, int physicsScore1, int physicsScore2, int physicsScore3,
			int chemScore1, int chemScore2, int chemScore3, int mathsScore1, int mathsScore2, int mathsScore3)
			throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue((totalPhysicsScore), "Scored Phy Marks", "");
		dataset.addValue((physicsScore1), "phy score 1", "");
		dataset.addValue((physicsScore2), "phy score 2", "");
		dataset.addValue((physicsScore3), "phy score 3", "");

		dataset.addValue((totalChemistryScore), "Scored chem Marks", "");
		dataset.addValue((chemScore1), "chem  score 1", "");
		dataset.addValue((chemScore2), "chem score 2", "");
		dataset.addValue((chemScore3), "chem  score 3", "");

		dataset.addValue((totalmathsScore), "Scored math Marks", "");
		dataset.addValue((mathsScore1), "math score 1", "");
		dataset.addValue((mathsScore2), "math score 2", "");
		dataset.addValue((mathsScore3), "math score 3", "");

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));
		plot.getDomainAxis().setCategoryMargin(2.0);

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setItemMargin(0.5);

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));

		renderer.setSeriesPaint(4, new Color(0, 0, 255));
		renderer.setSeriesPaint(5, new Color(255, 0, 0));

		renderer.setSeriesPaint(6, new Color(0, 255, 0));
		renderer.setSeriesPaint(7, new Color(255, 165, 0));

		renderer.setSeriesPaint(8, new Color(0, 0, 255));
		renderer.setSeriesPaint(9, new Color(255, 0, 0));

		renderer.setSeriesPaint(10, new Color(0, 255, 0));
		renderer.setSeriesPaint(11, new Color(255, 165, 0));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 8));
		xAxis.setAxisLineVisible(false);

		double d = totalPhysicsScore;

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(0.0, d);

		yAxis.setLabelFont(new Font("Arial", Font.BOLD, 8));

		Font tickLabelFont = new Font("Arial", Font.BOLD, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(2.0);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 8));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 10, 280, 580, 200);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart4(PDDocument document, PDPageContentStream contentStream, int physicsMarks,
			int chemistryMarks, int mathsMarks,String FirstSubject,String SecondSubject,String ThirdSubject) throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, (physicsMarks));
		dataset.setValue(SecondSubject, (chemistryMarks));
		dataset.setValue(ThirdSubject, (mathsMarks));

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));
		plot.setSectionPaint(SecondSubject, new Color(255, 0, 0));
		plot.setSectionPaint(ThirdSubject, new Color(0, 255, 0));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart1.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 415, 605, 210, 130);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart3(PDDocument document, PDPageContentStream contentStream, int totalPhysicsScore,
			int totalchemistryScore, int totalmathsScore,String FirstSubject,String SecondSubject,String ThirdSubject) throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, totalPhysicsScore);
		dataset.setValue(SecondSubject, totalchemistryScore);
		dataset.setValue(ThirdSubject, totalmathsScore);

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));
		plot.setSectionPaint(SecondSubject, new Color(255, 0, 0));
		plot.setSectionPaint(ThirdSubject, new Color(0, 255, 0));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 225, 605, 210, 130);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart2(PDDocument document, PDPageContentStream contentStream, int totalPhysicsScore,
			int totalchemistryScore, int totalmathsScore,String FirstSubject,String SecondSubject,String ThirdSubject) throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, totalPhysicsScore);
		dataset.setValue(SecondSubject, totalchemistryScore);
		dataset.setValue(ThirdSubject, totalmathsScore);

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));
		plot.setSectionPaint(SecondSubject, new Color(255, 0, 0));
		plot.setSectionPaint(ThirdSubject, new Color(0, 255, 0));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 30, 605, 210, 130);
	}

	private static void addBarChart2(PDPageContentStream contentStream, String phyScoredmarks, PDDocument document,
			String maxMarks, int averagePhyScoredMarks, List<StudentPhyScore> top5phyStudents, String chemScoredmarks,
			int PhyMaxMarks, List<StudentChemScore> top5chemStudents, int averageChemScoredMarks,
			List<StudentMathScore> top5mathStudents, String mathsScoredmarks, int averageMathScoredMarks)
			throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(Integer.parseInt(phyScoredmarks), "Scored Phy Marks", "");
		dataset.addValue((averagePhyScoredMarks), "Average Marks", "");

		int topCount = 1;
		for (StudentPhyScore studentScore : top5phyStudents) {
			dataset.addValue(studentScore.getScore(), "Top Marks " + topCount++, "");
		}

		dataset.addValue(Integer.parseInt(chemScoredmarks), "Scored Chem Marks", "");
		dataset.addValue((averageChemScoredMarks), "Average Chem Marks", "");
		int topCount0 = 1;
		for (StudentChemScore studentScore : top5chemStudents) {
			dataset.addValue(studentScore.getScore(), "Top Chem Marks " + topCount0++, "");
		}

		dataset.addValue(Integer.parseInt(mathsScoredmarks), "Scored math Marks", "");
		dataset.addValue((averageMathScoredMarks), "Average Math Marks", "");
		int topCount1 = 1;
		for (StudentMathScore studentScore : top5mathStudents) {
			dataset.addValue(studentScore.getScore(), "Top Math Marks " + topCount1++, "");
		}

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));
		plot.getDomainAxis().setCategoryMargin(2.0);

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setItemMargin(0.5);

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(255, 255, 0));
		renderer.setSeriesPaint(5, new Color(162, 32, 240));
		renderer.setSeriesPaint(6, new Color(172, 216, 230));
		renderer.setSeriesPaint(7, new Color(0, 0, 255));
		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(8, new Color(255, 0, 0));

		renderer.setSeriesPaint(9, new Color(0, 255, 0));
		renderer.setSeriesPaint(10, new Color(255, 165, 0));
		renderer.setSeriesPaint(11, new Color(255, 255, 0));
		renderer.setSeriesPaint(12, new Color(162, 32, 240));
		renderer.setSeriesPaint(13, new Color(172, 216, 230));
		renderer.setSeriesPaint(14, new Color(0, 0, 255));
		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(15, new Color(255, 0, 0));

		renderer.setSeriesPaint(16, new Color(0, 255, 0));
		renderer.setSeriesPaint(17, new Color(255, 165, 0));
		renderer.setSeriesPaint(18, new Color(255, 255, 0));
		renderer.setSeriesPaint(19, new Color(162, 32, 240));
		renderer.setSeriesPaint(20, new Color(172, 216, 230));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 8));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(averagePhyScoredMarks, PhyMaxMarks);

		yAxis.setLabelFont(new Font("Arial", Font.BOLD, 8));

		Font tickLabelFont = new Font("Arial", Font.BOLD, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(2.0);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 8));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 10, 265, 580, 200);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void addPieChart(PDPageContentStream contentStream, int correct, int incorrect, int unattempted,
			PDDocument document) throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Correct", correct);
		dataset.setValue("Incorrect", incorrect);
		dataset.setValue("Unattempted", unattempted);

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint("Correct", new Color(0, 0, 255));
		plot.setSectionPaint("Incorrect", new Color(255, 0, 0));
		plot.setSectionPaint("Unattempted", new Color(0, 255, 0));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 375, 550, 250, 150);
	}

	private static void addBarChart(PDPageContentStream contentStream, String scoredMarks, PDDocument document,
			String maxMarks, double averageScoredMarks, List<StudentScore> top5Students) throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(Integer.parseInt(scoredMarks), "Scored Marks", "");
		dataset.addValue((averageScoredMarks), "Average Marks", "");

		int topCount = 1;
		for (StudentScore studentScore : top5Students) {
			dataset.addValue(studentScore.getScore(), "Top Marks " + topCount++, "");
		}

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));

		BarRenderer renderer = (BarRenderer) plot.getRenderer();

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(255, 255, 0));
		renderer.setSeriesPaint(5, new Color(162, 32, 240));
		renderer.setSeriesPaint(6, new Color(172, 216, 230));
		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 10));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(-12, Integer.parseInt(maxMarks));
		yAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 7));
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		Font tickLabelFont = new Font("Arial", Font.PLAIN, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(1);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 10));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 15, 520, CHART_WIDTH,
				CHART_HEIGHT);
	}

	private static void addPageNumber(PDDocument document, String imageName, PDPageContentStream contentStream,
			int pageNumber) throws IOException {

		String pageNumberText = "Page " + pageNumber + " of " + document.getNumberOfPages();

		float textX = 280;
		float textY = 20;

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 8);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(20, 20);
		contentStream.showText(imageName + "[5000041]");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 8);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX, textY));
		contentStream.showText(pageNumberText);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 8);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(450, 20);
		contentStream.showText("Regular Class Test (Std Sample OMR)");
		contentStream.endText();
	}

	/*
	 * 
	 * 
	 * 
	 * Section2
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	private static void addBarChart2Section2(PDPageContentStream contentStream, String phyScoredmarks,
			PDDocument document, String maxMarks, int averagePhyScoredMarks, List<StudentPhyScore> top5phyStudents,
			int PhyMaxMarks, List<StudentMathScore> top5mathStudents, String mathsScoredmarks,
			int averageMathScoredMarks) throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(Integer.parseInt(phyScoredmarks), "Scored Phy Marks", "");
		dataset.addValue((averagePhyScoredMarks), "Average Marks", "");

		int topCount = 1;
		for (StudentPhyScore studentScore : top5phyStudents) {
			dataset.addValue(studentScore.getScore(), "Top Marks " + topCount++, "");
		}

		dataset.addValue(Integer.parseInt(mathsScoredmarks), "Scored math Marks", "");
		dataset.addValue((averageMathScoredMarks), "Average Math Marks", "");
		int topCount1 = 1;
		for (StudentMathScore studentScore : top5mathStudents) {
			dataset.addValue(studentScore.getScore(), "Top Math Marks " + topCount1++, "");
		}

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));
		plot.getDomainAxis().setCategoryMargin(2.0);

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setItemMargin(0.5);

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(255, 255, 0));
		renderer.setSeriesPaint(5, new Color(162, 32, 240));
		renderer.setSeriesPaint(6, new Color(172, 216, 230));
		renderer.setSeriesPaint(7, new Color(0, 0, 255));
		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(8, new Color(255, 0, 0));

		renderer.setSeriesPaint(9, new Color(0, 255, 0));
		renderer.setSeriesPaint(10, new Color(255, 165, 0));
		renderer.setSeriesPaint(11, new Color(255, 255, 0));
		renderer.setSeriesPaint(12, new Color(162, 32, 240));
		renderer.setSeriesPaint(13, new Color(172, 216, 230));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 8));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(averagePhyScoredMarks, PhyMaxMarks);

		yAxis.setLabelFont(new Font("Arial", Font.BOLD, 8));

		Font tickLabelFont = new Font("Arial", Font.BOLD, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(2.0);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 8));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 10, 265, 580, 200);
	}

	/*
	 * 
	 * 
	 * page 3 section 3
	 * 
	 * 
	 * 
	 * 
	 */
	private static void addContentTopage3Section2(String imageName, PDPage page3, PDPageContentStream contentStream,
			PDDocument document, int totalPhysicsScore, int totalmathsScore, int physicsMarks, int mathsMarks,
			int attemptmathQuestions, int mathsScore1, int mathsScore2, String mathspercentage,
			int attemptphysicsQuestions, int physicsScore1, int physicsScore2, String physicspercentage,
			List<StudentMathScore> studentmathScores, List<StudentPhyScore> studentphyScores, int physicsScore3,
			int mathsScore3, List<StudentPositiveScore> top5Positive, String PositiveScore,
			List<StudentNegativeScore> top5Negative, String NegativeScore,String FirstSubject,String SecondSubject) throws IOException {

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(80, 750);
		contentStream.showText("Count of Questions");
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(270, 750);
		contentStream.showText("Marks of Questions");
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(480, 750);
		contentStream.showText("My Score");
		contentStream.endText();

		addPieChart2section2(document, contentStream, totalPhysicsScore, totalmathsScore,FirstSubject,SecondSubject);
		addPieChart3section2(document, contentStream, totalPhysicsScore, totalmathsScore,FirstSubject,SecondSubject);
		if (physicsMarks < 0 && mathsMarks < 0) {

			float x = 450;
			float y = 680;
			float width = 100;
			float height = 20;

			contentStream.addRect(x, y, width, height);
			contentStream.setNonStrokingColor(new Color(255, 127, 127));
			contentStream.fill();

			contentStream.setStrokingColor(Color.BLACK);
			contentStream.addRect(x, y, width, height);
			contentStream.stroke();

			contentStream.beginText();
			contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
			contentStream.setNonStrokingColor(Color.BLACK);
			contentStream.newLineAtOffset(x + 10, y + 5);
			contentStream.showText("Negative Score");
			contentStream.endText();
		} else {
			addPieChart4Section2(document, contentStream, physicsMarks, mathsMarks,FirstSubject,SecondSubject);

		}
		int initX = 50;
		int initY = 600;
		int cellHeight = 20;
		int cellWidth = 60;

		int colCount = 9;
		int rowCount = 3;

		String targetImageName = imageName;
		int mathrank = getStudentRank(studentmathScores, targetImageName);
		int phyrank = getphyStudentRank(studentphyScores, targetImageName);

		String[][] tableData = {
				{ "Subject", "Total Q.", "Attempt Q.", "Correct", "Incorrect", "Raw Score", "Total(Max)", "Percent",
						"Rank" },
			
				{FirstSubject, Integer.toString(totalPhysicsScore), Integer.toString(attemptphysicsQuestions),
						Integer.toString(physicsScore1), Integer.toString(physicsScore2),
						Integer.toString(physicsMarks), Integer.toString(totalPhysicsScore), physicspercentage,
						Integer.toString(phyrank) },
				{ SecondSubject, Integer.toString(totalmathsScore), Integer.toString(attemptmathQuestions),
							Integer.toString(mathsScore1), Integer.toString(mathsScore2), Integer.toString(mathsMarks),
							Integer.toString(totalmathsScore), mathspercentage, Integer.toString(mathrank) },

		};

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				String content = tableData[i][j];

				PDType1Font font = (i == 0) ? new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
						: new PDType1Font(Standard14Fonts.FontName.HELVETICA);

				contentStream.addRect(initX, initY, cellWidth, -cellHeight);

				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.newLineAtOffset(initX + 10, initY - cellHeight + 5);
				contentStream.showText(content);
				contentStream.endText();

				initX += cellWidth;
			}
			initX = 50;
			initY -= cellHeight;
		}

		contentStream.stroke();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));

		float x = 20;
		float y = 495;
		float width = 570;
		float height = 20;

		contentStream.addRect(x, y, width, height);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x + 5, y + 5);
		contentStream.showText("Analysis of Overall Test");
		contentStream.endText();

		addBarChart31Section2(contentStream, totalPhysicsScore, totalmathsScore, document, physicsScore1, physicsScore2,
				physicsScore3, mathsScore1, mathsScore2, mathsScore3);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(140, 270);
		contentStream.showText(FirstSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(480, 270);
		contentStream.showText(SecondSubject);
		contentStream.endText();

		float x2 = 140;
		float y2 = 240;
		float width2 = 355;
		float height2 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x2, y2, width2, height2);
		contentStream.stroke();

		float innerX = x2 + 30;
		float innerY = y2 + 3;
		float innerWidth = 10;
		float innerHeight = height2 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerX, innerY, innerWidth, innerHeight);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX + 14, innerY + 1);
		contentStream.showText("Total Questions");
		contentStream.endText();

		float innerX1 = x2 + 110;
		float innerY1 = y2 + 3;
		float innerWidth1 = 10;
		float innerHeight1 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerX1, innerY1, innerWidth1, innerHeight1);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX1 + 14, innerY1 + 1);
		contentStream.showText("Correct Questions");
		contentStream.endText();

		float innerX2 = x2 + 200;
		float innerY2 = y2 + 3;
		float innerWidth2 = 10;
		float innerHeight2 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerX2, innerY2, innerWidth2, innerHeight2);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX2 + 14, innerY2 + 1);
		contentStream.showText("Incorrect Questions");
		contentStream.endText();

		float innerX3 = x2 + 300;
		float innerY3 = y2 + 3;
		float innerWidth3 = 10;
		float innerHeight3 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerX3, innerY3, innerWidth3, innerHeight3);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX3 + 14, innerY3 + 1);
		contentStream.showText("Skipped");
		contentStream.endText();
		contentStream.setNonStrokingColor(new Color(255, 255, 0));

		float x3 = 20;
		float y3 = 200;
		float width3 = 570;
		float height3 = 20;

		contentStream.addRect(x3, y3, width3, height3);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 5, y3 + 5);
		contentStream.showText("Comparison of Positive Score");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 300, y3 + 5);
		contentStream.showText("Comparison of Negative Score");
		contentStream.endText();

		addBarChart3(contentStream, totalPhysicsScore, document, top5Positive, PositiveScore);
		addBarChart4(contentStream, totalPhysicsScore, document, top5Negative, NegativeScore);

		float x4 = 100;
		float y4 = 40;
		float width4 = 355;
		float height4 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x4, y4, width4, height4);
		contentStream.stroke();

		float innerXa = x4 + 30;
		float innerYa = y4 + 3;
		float innerWidtha = 10;
		float innerHeighta = height4 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerXa, innerYa, innerWidtha, innerHeighta);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXa + 14, innerYa + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerXb = x4 + 85;
		float innerYb = y4 + 3;
		float innerWidthb = 10;
		float innerHeightb = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerXb, innerYb, innerWidthb, innerHeightb);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXb + 14, innerYb + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerXc = x4 + 130;
		float innerYc = y4 + 3;
		float innerWidthc = 10;
		float innerHeightc = height4 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerXc, innerYc, innerWidthc, innerHeightc);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXc + 14, innerYc + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerXd = x4 + 175;
		float innerYd = y4 + 3;
		float innerWidthd = 10;
		float innerHeightd = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerXd, innerYd, innerWidthd, innerHeightd);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXd + 14, innerYd + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		float innerX4 = x4 + 220;
		float innerY4 = y4 + 3;
		float innerWidth4 = 10;
		float innerHeight4 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 255, 0));
		contentStream.addRect(innerX4, innerY4, innerWidth4, innerHeight4);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX4 + 14, innerY4 + 1);
		contentStream.showText("Rank 4");
		contentStream.endText();

		float innerX5 = x4 + 270;
		float innerY5 = y4 + 3;
		float innerWidth5 = 10;
		float innerHeight5 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(162, 32, 240));
		contentStream.addRect(innerX5, innerY5, innerWidth5, innerHeight5);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX5 + 14, innerY5 + 1);
		contentStream.showText("Rank 5");
		contentStream.endText();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart2section2(PDDocument document, PDPageContentStream contentStream,
			int totalPhysicsScore, int totalmathsScore,String FirstSubject,String SecondSubject) throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, totalPhysicsScore);

		dataset.setValue(SecondSubject, totalmathsScore);

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));

		plot.setSectionPaint(SecondSubject, new Color(0, 255, 0));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 30, 605, 210, 130);
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart3section2(PDDocument document, PDPageContentStream contentStream,
			int totalPhysicsScore, int totalmathsScore,String FirstSubject,String SecondSubject) throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, totalPhysicsScore);

		dataset.setValue(SecondSubject, totalmathsScore);

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));

		plot.setSectionPaint(SecondSubject, new Color(0, 255, 0));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 225, 605, 210, 130);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart4Section2(PDDocument document, PDPageContentStream contentStream, int physicsMarks,
			int mathsMarks,String FirstSubject,String SecondSubject) throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, (physicsMarks));

		dataset.setValue(SecondSubject, (mathsMarks));

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));

		plot.setSectionPaint(SecondSubject, new Color(0, 255, 0));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart1.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 415, 605, 210, 130);
	}

	private static void addBarChart31Section2(PDPageContentStream contentStream, int totalPhysicsScore,
			int totalmathsScore, PDDocument document, int physicsScore1, int physicsScore2, int physicsScore3,
			int mathsScore1, int mathsScore2, int mathsScore3) throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue((totalPhysicsScore), "Scored Phy Marks", "");
		dataset.addValue((physicsScore1), "phy score 1", "");
		dataset.addValue((physicsScore2), "phy score 2", "");
		dataset.addValue((physicsScore3), "phy score 3", "");

		dataset.addValue((totalmathsScore), "Scored math Marks", "");
		dataset.addValue((mathsScore1), "math score 1", "");
		dataset.addValue((mathsScore2), "math score 2", "");
		dataset.addValue((mathsScore3), "math score 3", "");

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));
		plot.getDomainAxis().setCategoryMargin(2.0);

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setItemMargin(0.5);

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(0, 0, 255));
		renderer.setSeriesPaint(5, new Color(255, 0, 0));

		renderer.setSeriesPaint(6, new Color(0, 255, 0));
		renderer.setSeriesPaint(7, new Color(255, 165, 0));
		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 8));
		xAxis.setAxisLineVisible(false);

		double d = totalPhysicsScore;

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(0.0, d);

		yAxis.setLabelFont(new Font("Arial", Font.BOLD, 8));

		Font tickLabelFont = new Font("Arial", Font.BOLD, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(2.0);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 8));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 10, 280, 580, 200);
	}

	@SuppressWarnings("unused")
	private static void addContentTopage4Section1(PDPageContentStream contentStream, PDDocument document,
			int physicsScore1, int physicsScore2, List<StudentPhysicsPositiveScore> top5PhysicsPositive,
			List<StudentPhysicsNegativeScore> top5PhysicsNegative,String FirstSubject) throws IOException {
		contentStream.setNonStrokingColor(new Color(0, 149, 193));

		float x3 = 20;
		float y3 = 750;
		float width3 = 570;
		float height3 = 20;

		contentStream.addRect(x3, y3, width3, height3);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 5, y3 + 5);
		contentStream.showText("Distribution of Section wise Positive Score");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 300, y3 + 5);
		contentStream.showText("Distribution of Section wise Negative Score");
		contentStream.endText();

		addPieChart5Section1(contentStream, document, physicsScore1,FirstSubject);
		addPieChart6Section1(contentStream, document, physicsScore2,FirstSubject);

		contentStream.setNonStrokingColor(new Color(0, 149, 193));

		float x4 = 20;
		float y4 = 580;
		float width4 = 570;
		float height4 = 20;

		contentStream.addRect(x4, y4, width4, height4);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x4 + 5, y4 + 5);
		contentStream.showText("Comparision of Section wise Positive Score");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x4 + 300, y4 + 5);
		contentStream.showText("Comparision of Section wise Negative Score");
		contentStream.endText();

		addBarChart5Section1(contentStream, document, physicsScore1, top5PhysicsPositive);
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(140, 432);
		contentStream.showText(FirstSubject);
		contentStream.endText();

		addBarChart6Section1(contentStream, document, physicsScore2, top5PhysicsNegative);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(400, 432);
		contentStream.showText(FirstSubject);
		contentStream.endText();

		float x41 = 120;
		float y41 = 400;
		float width41 = 355;
		float height41 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x41, y41, width41, height41);
		contentStream.stroke();

		float innerXa = x41 + 30;
		float innerYa = y41 + 3;
		float innerWidtha = 10;
		float innerHeighta = height41 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerXa, innerYa, innerWidtha, innerHeighta);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXa + 14, innerYa + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerXb = x41 + 85;
		float innerYb = y41 + 3;
		float innerWidthb = 10;
		float innerHeightb = height41 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerXb, innerYb, innerWidthb, innerHeightb);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXb + 14, innerYb + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerXc = x41 + 130;
		float innerYc = y41 + 3;
		float innerWidthc = 10;
		float innerHeightc = height41 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerXc, innerYc, innerWidthc, innerHeightc);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXc + 14, innerYc + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerXd = x41 + 180;
		float innerYd = y41 + 3;
		float innerWidthd = 10;
		float innerHeightd = height41 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerXd, innerYd, innerWidthd, innerHeightd);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXd + 14, innerYd + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		float innerX4 = x41 + 230;
		float innerY4 = y41 + 3;
		float innerWidth4 = 10;
		float innerHeight4 = height41 - 6;

		contentStream.setNonStrokingColor(new Color(255, 255, 0));
		contentStream.addRect(innerX4, innerY4, innerWidth4, innerHeight4);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX4 + 14, innerY4 + 1);
		contentStream.showText("Rank 4");
		contentStream.endText();

		float innerX5 = x41 + 280;
		float innerY5 = y41 + 3;
		float innerWidth5 = 10;
		float innerHeight5 = height41 - 6;

		contentStream.setNonStrokingColor(new Color(162, 32, 240));
		contentStream.addRect(innerX5, innerY5, innerWidth5, innerHeight5);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX5 + 14, innerY5 + 1);
		contentStream.showText("Rank 5");
		contentStream.endText();

		int initX = 50;
		int initY = 380;
		int cellHeight = 20;
		int cellWidth = 130;

		int colCount = 1;
		int rowCount = 3;

		String[][] tableData = { { "Subject" }, { "" }, {FirstSubject },

		};

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				String content = tableData[i][j];

				PDType1Font font = (i == 0) ? new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
						: new PDType1Font(Standard14Fonts.FontName.HELVETICA);

				contentStream.addRect(initX, initY, cellWidth, -cellHeight);

				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.newLineAtOffset(initX + 40, initY - cellHeight + 5);
				contentStream.showText(content);
				contentStream.endText();

				initX += cellWidth;
			}
			initX = 50;
			initY -= cellHeight;
		}

		contentStream.stroke();
		int initX1 = 180;
		int initY1 = 380;
		int cellHeight1 = 20;
		int cellWidth1 = 30;

		int colCount1 = 6;
		int rowCount1 = 4;

		String[] headers = { "My Score", "Rank 1", "Rank 2", "Rank 3", "Rank 4", "Rank 5" };
		String[] subHeaders = { "+ve", "-ve", "+ve", "-ve", "+ve", "-ve", "+ve", "-ve", "+ve", "-ve", "+ve", "-ve" };

		int t1pp = top5PhysicsPositive.get(0).getScore();
		int t2pp = top5PhysicsPositive.get(1).getScore();
		int t3pp = top5PhysicsPositive.get(2).getScore();
		int t4pp = top5PhysicsPositive.get(3).getScore();
		int t5pp = top5PhysicsPositive.get(4).getScore();

		int t1pn = top5PhysicsNegative.get(0).getScore();
		int t2pn = top5PhysicsNegative.get(1).getScore();
		int t3pn = top5PhysicsNegative.get(2).getScore();
		int t4pn = top5PhysicsNegative.get(3).getScore();
		int t5pn = top5PhysicsNegative.get(4).getScore();

		String[][] tableData1 = { { Integer.toString(physicsScore1), Integer.toString(physicsScore2),
				Integer.toString(t1pp), Integer.toString(t1pn), Integer.toString(t2pp), Integer.toString(t2pn),
				Integer.toString(t3pp), Integer.toString(t3pn), Integer.toString(t4pp), Integer.toString(t4pn),
				Integer.toString(t5pp), Integer.toString(t5pn) }, };

		for (int i = 0; i < headers.length; i++) {
			String content = headers[i];
			contentStream.addRect(initX1, initY1, cellWidth1 * 2, -cellHeight1);
			contentStream.beginText();
			contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 9);
			contentStream.setNonStrokingColor(Color.BLACK);
			contentStream.newLineAtOffset(initX1 + 10, initY1 - cellHeight1 + 5);
			contentStream.showText(content);
			contentStream.endText();
			initX1 += cellWidth1 * 2;
		}
		initX1 = 180;
		initY1 -= cellHeight1;

		for (int i = 0; i < subHeaders.length; i++) {
			String content = subHeaders[i];
			contentStream.addRect(initX1, initY1, cellWidth1, -cellHeight1);
			contentStream.beginText();
			contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 9);
			contentStream.setNonStrokingColor(Color.BLACK);
			contentStream.newLineAtOffset(initX1 + 10, initY1 - cellHeight1 + 5);
			contentStream.showText(content);
			contentStream.endText();
			initX1 += cellWidth1;
		}
		initX1 = 180;
		initY1 -= cellHeight1;

		for (int i = 0; i < tableData1.length; i++) {
			for (int j = 0; j < tableData1[i].length; j++) {
				String content = tableData1[i][j];
				contentStream.addRect(initX1, initY1, cellWidth1, -cellHeight1);
				contentStream.beginText();
				contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.newLineAtOffset(initX1 + 10, initY1 - cellHeight1 + 5);
				contentStream.showText(content);
				contentStream.endText();
				initX1 += cellWidth1;
			}
			initX1 = 180;
			initY1 -= cellHeight1;
		}

		contentStream.stroke();

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void addPieChart6Section1(PDPageContentStream contentStream, PDDocument document, int physicsScore2,String FirstSubject)
			throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, (physicsScore2));

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart1.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 350, 600, 230, 140);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart5Section1(PDPageContentStream contentStream, PDDocument document, int physicsScore1,String FirstSubject)
			throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, (physicsScore1));

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));

	

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 50, 600, 230, 140);
	}

	private static void addBarChart6Section1(PDPageContentStream contentStream, PDDocument document, int physicsScore2,
			List<StudentPhysicsNegativeScore> top5PhysicsNegative) throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue((physicsScore2), "phy positive Marks", "");
		int topCount = 1;
		for (StudentPhysicsNegativeScore studentScore : top5PhysicsNegative) {
			dataset.addValue(studentScore.getScore(), "phy Top Marks " + topCount++, "");
		}

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));

		BarRenderer renderer = (BarRenderer) plot.getRenderer();

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(255, 255, 0));
		renderer.setSeriesPaint(5, new Color(162, 32, 240));

		renderer.setSeriesPaint(6, new Color(0, 0, 255));
		renderer.setSeriesPaint(7, new Color(255, 0, 0));

		renderer.setSeriesPaint(8, new Color(0, 255, 0));
		renderer.setSeriesPaint(9, new Color(255, 165, 0));
		renderer.setSeriesPaint(10, new Color(255, 255, 0));
		renderer.setSeriesPaint(11, new Color(162, 32, 240));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 10));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(0, 12);
		yAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 7));
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		Font tickLabelFont = new Font("Arial", Font.PLAIN, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(1);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 10));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 300, 430, 270, 145);
	}

	private static void addBarChart5Section1(PDPageContentStream contentStream, PDDocument document, int physicsScore1,
			List<StudentPhysicsPositiveScore> top5PhysicsPositive) throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue((physicsScore1), "phy positive Marks", "");
		int topCount = 1;
		for (StudentPhysicsPositiveScore studentScore : top5PhysicsPositive) {
			dataset.addValue(studentScore.getScore(), "phy Top Marks " + topCount++, "");
		}

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));

		BarRenderer renderer = (BarRenderer) plot.getRenderer();

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(255, 255, 0));
		renderer.setSeriesPaint(5, new Color(162, 32, 240));

		renderer.setSeriesPaint(6, new Color(0, 0, 255));
		renderer.setSeriesPaint(7, new Color(255, 0, 0));

		renderer.setSeriesPaint(8, new Color(0, 255, 0));
		renderer.setSeriesPaint(9, new Color(255, 165, 0));
		renderer.setSeriesPaint(10, new Color(255, 255, 0));
		renderer.setSeriesPaint(11, new Color(162, 32, 240));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 10));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(0, 12);
		yAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 7));
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		Font tickLabelFont = new Font("Arial", Font.PLAIN, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(1);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 10));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 15, 430, 270, 145);
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Section 1
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	@SuppressWarnings("unused")
	private static void addContentToPage1Section1(PDDocument document, PDPage page, PDPageContentStream contentStream,
			String imageName, int totalStudents, String scoredMarks, String maxMarks, String percentage,
			int physicsMarks, int totalPhysicsScore, String physicspercentage, String fileNameWithoutExtension,String FirstSubject)
			throws IOException {

		float[] textColor1 = hexToRGB("#F5C300");

		String scoreReportBelowText = fileNameWithoutExtension;
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 26);
		contentStream.setNonStrokingColor(textColor1[0], textColor1[1], textColor1[2]);
		float textWidth1 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(scoreReportBelowText)
				/ 1000f * 40;
		float textX1 = 70;
		float textY1 = 565;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX1, textY1));
		contentStream.showText(scoreReportBelowText);
		contentStream.endText();

		float[] textColor = hexToRGB("#6978aa");

		String scoreReportText = "Score Report";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 46);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(scoreReportText) / 1000f
				* 40;
		float textX = 70;
		float textY = 600;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX, textY));
		contentStream.showText(scoreReportText);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 46);
		contentStream.setNonStrokingColor(textColor[0], textColor[1], textColor[2]);
		float shadowOffsetX = 1;
		float shadowOffsetY = 1;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX + shadowOffsetX, textY - shadowOffsetY));
		contentStream.showText(scoreReportText);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 430);
		contentStream.showText(Integer.toString(totalStudents));
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 390);
		contentStream.showText(scoredMarks + "/" + maxMarks + "(" + percentage + ")");
		contentStream.endText();

		/*
		 * contentStream.beginText(); contentStream.setFont(new
		 * PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		 * contentStream.setNonStrokingColor(0f, 0f, 0f);
		 * contentStream.newLineAtOffset(425, 390); contentStream.showText(maxMarks);
		 * contentStream.endText();
		 */
		/*
		 * contentStream.beginText(); contentStream.setFont(new
		 * PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		 * contentStream.setNonStrokingColor(0f, 0f, 0f);
		 * contentStream.newLineAtOffset(443, 390);
		 * contentStream.showText("("+percentage+")"); contentStream.endText();
		 */
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(200, 355);
		contentStream.showText(FirstSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 355);
		contentStream
				.showText(physicsMarks + "/" + Integer.toString(totalPhysicsScore) + "(" + physicspercentage + ")");
		contentStream.endText();

		String candidateIDText = "Candidate ID";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth2 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(candidateIDText) / 1000f
				* 40;
		float textX2 = 200;
		float textY2 = 490;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX2, textY2));
		contentStream.showText(candidateIDText);
		contentStream.endText();

		String candidateIDansText = "5000041";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth3 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(candidateIDansText)
				/ 1000f * 40;
		float textX3 = 400;
		float textY3 = 490;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX3, textY3));
		contentStream.showText(candidateIDansText);
		contentStream.endText();

		String assessmentDateText = "Assessment Date";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth4 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(assessmentDateText)
				/ 1000f * 40;
		float textX4 = 200;
		float textY4 = 460;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX4, textY4));
		contentStream.showText(assessmentDateText);
		contentStream.endText();

		String assessmentdateansText = "July 16,2024";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth5 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(assessmentdateansText)
				/ 1000f * 40;
		float textX5 = 400;
		float textY5 = 460;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX5, textY5));
		contentStream.showText(assessmentdateansText);
		contentStream.endText();

		String TotalcanText = "Total Candidates";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth6 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(TotalcanText) / 1000f
				* 40;
		float textX6 = 200;
		float textY6 = 430;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX6, textY6));
		contentStream.showText(TotalcanText);
		contentStream.endText();

		String RawscoreText = "My Raw Score";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth7 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(RawscoreText) / 1000f
				* 40;
		float textX7 = 200;
		float textY7 = 390;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX7, textY7));
		contentStream.showText(RawscoreText);
		contentStream.endText();

		String imagePath = "C:\\Users\\kusha\\OneDrive\\Desktop\\workspace\\pdfbuilder\\Picture1.jpg";
		PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath, document);
		float imageWidth = pdImage.getWidth();
		float imageHeight = pdImage.getHeight();
		float pageWidth = page.getMediaBox().getWidth();
		float pageHeight = page.getMediaBox().getHeight();
		float padding = 20;
		float x = padding;
		float y = 30;
		float scaledWidth = pageWidth - 2 * padding;
		float scaledHeight = imageHeight * (scaledWidth / imageWidth);
		contentStream.drawImage(pdImage, x, y, scaledWidth, scaledHeight);

		String text = "Demo Institution | Institución de demostración";
		float fontSize = 17;
		float textWidthinst = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD).getStringWidth(text) / 1000
				* fontSize;
		float textXinst = 40;
		float textYinst = y + scaledHeight - 200;

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 17);
		contentStream.setNonStrokingColor(1f, 1f, 1f);
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textXinst, textYinst));
		contentStream.showText(text);
		contentStream.endText();

		String imagePath2 = "C:\\Users\\kusha\\OneDrive\\Desktop\\workspace\\pdfbuilder\\Picture2.png";
		PDImageXObject pdImage2 = PDImageXObject.createFromFile(imagePath2, document);
		float imageWidth2 = pdImage2.getWidth();
		float imageHeight2 = pdImage2.getHeight();
		float pageWidth2 = page.getMediaBox().getWidth();
		float pageHeight2 = page.getMediaBox().getHeight();
		float padding2 = 20;
		float x2 = padding2;
		float y2 = 694;
		float scaledWidth2 = pageWidth2 - 2 * padding2;
		float scaledHeight2 = imageHeight2 * (scaledWidth2 / imageWidth2);
		contentStream.drawImage(pdImage2, x2, y2, scaledWidth2, scaledHeight2);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 23);
		contentStream.setNonStrokingColor(1f, 1f, 1f);
		float textXStudentName = x2 + 420;
		float textYStudentName = y2 + 20;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textXStudentName, textYStudentName));
		contentStream.showText(imageName);
		contentStream.endText();

	}

	private static void addContentTopage3Section1(PDPage page2, PDPageContentStream contentStream, String scoredMarks,
			PDDocument document, String maxMarks, double averageScoredMarks, List<StudentScore> top5Students,
			String correct, String incorrect, String unattempted, String phyScoredmarks, int averagePhyScoredMarks,
			List<StudentPhyScore> top5phyStudents, int PhyMaxMarks,String FirstSubject) throws IOException {

		contentStream.setNonStrokingColor(Color.YELLOW);

		float x = 20;
		float y = 750;
		float width = 570;
		float height = 20;

		contentStream.addRect(x, y, width, height);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x + 5, y + 5);
		contentStream.showText("Analysis of Overall Test");
		contentStream.endText();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));
		float x1 = 20;
		float y1 = 727;
		float width1 = 570;
		float height1 = 20;

		contentStream.addRect(x1, y1, width1, height1);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x1 + 5, y1 + 5);
		contentStream.showText("Comparison of Test Score");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 8);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(460, 715);
		contentStream.showText("My Correct, Incorrect &");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 8);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(448, 705);
		contentStream.showText("Unattempted Questions Count");
		contentStream.endText();

		addBarChart(contentStream, scoredMarks, document, maxMarks, averageScoredMarks, top5Students);
		addPieChart(contentStream, Integer.parseInt(correct), Integer.parseInt(incorrect),
				Integer.parseInt(unattempted), document);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(460, 550);
		contentStream.showText("Total Questions : " + maxMarks);
		contentStream.endText();

		int Attempt = Integer.parseInt(maxMarks) - Integer.parseInt(unattempted);
		float Attemptpercent = (Attempt / Integer.parseInt(maxMarks)) * 100;

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(469, 540);
		contentStream.showText("Attempt : " + Attemptpercent + " %");
		contentStream.endText();

		int Correct = Integer.parseInt(correct);
		int Max = Integer.parseInt(maxMarks);

		float temp = (Correct * 100) / Max;

		int Incorrect = Integer.parseInt(incorrect);
		float IncorrectPercent = (Incorrect * 100) / Max;

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(400, 515);
		contentStream.showText("Correct Ans : " + temp + "%" + " / " + "Incorrect Ans : " + IncorrectPercent + "%");
		contentStream.endText();

		float x2 = 40;
		float y2 = 510;
		float width2 = 355;
		float height2 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x2, y2, width2, height2);
		contentStream.stroke();

		float innerX = x2 + 5;
		float innerY = y2 + 3;
		float innerWidth = 10;
		float innerHeight = height2 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerX, innerY, innerWidth, innerHeight);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX + 14, innerY + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerX1 = x2 + 60;
		float innerY1 = y2 + 3;
		float innerWidth1 = 10;
		float innerHeight1 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerX1, innerY1, innerWidth1, innerHeight1);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX1 + 14, innerY1 + 1);
		contentStream.showText("Avg Score");
		contentStream.endText();

		float innerX2 = x2 + 120;
		float innerY2 = y2 + 3;
		float innerWidth2 = 10;
		float innerHeight2 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerX2, innerY2, innerWidth2, innerHeight2);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX2 + 14, innerY2 + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerX3 = x2 + 165;
		float innerY3 = y2 + 3;
		float innerWidth3 = 10;
		float innerHeight3 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerX3, innerY3, innerWidth3, innerHeight3);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX3 + 14, innerY3 + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerX4 = x2 + 210;
		float innerY4 = y2 + 3;
		float innerWidth4 = 10;
		float innerHeight4 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 255, 0));
		contentStream.addRect(innerX4, innerY4, innerWidth4, innerHeight4);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX4 + 14, innerY4 + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		float innerX5 = x2 + 260;
		float innerY5 = y2 + 3;
		float innerWidth5 = 10;
		float innerHeight5 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(160, 32, 240));
		contentStream.addRect(innerX5, innerY5, innerWidth5, innerHeight5);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX5 + 14, innerY5 + 1);
		contentStream.showText("Rank 4");
		contentStream.endText();

		float innerX6 = x2 + 310;
		float innerY6 = y2 + 3;
		float innerWidth6 = 10;
		float innerHeight6 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(173, 216, 230));
		contentStream.addRect(innerX6, innerY6, innerWidth6, innerHeight6);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX6 + 14, innerY6 + 1);
		contentStream.showText("Rank 5");
		contentStream.endText();

		float sectionX = 30;
		float sectionY = 495;

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(sectionX, sectionY);
		contentStream.showText("Test Toppers : ");

		StringBuilder topStudentsLine = new StringBuilder();
		for (int i = 0; i < Math.min(5, top5Students.size()); i++) {
			StudentScore student = top5Students.get(i);
			contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
			topStudentsLine.append(student.getName()).append(" (Score: ").append(student.getScore()).append(")");
			if (i < Math.min(5, top5Students.size()) - 1) {
				topStudentsLine.append(", ");
			}
		}
		contentStream.showText(topStudentsLine.toString());

		contentStream.endText();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));
		float x3 = 20;
		float y3 = 470;
		float width3 = 570;
		float height3 = 20;

		contentStream.addRect(x3, y3, width3, height3);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 5, y3 + 5);
		contentStream.showText("Comparison of Section wise Score");
		contentStream.endText();

		addBarChart2Section1(contentStream, phyScoredmarks, document, maxMarks, averagePhyScoredMarks, top5phyStudents,
				PhyMaxMarks);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(250, 270);
		contentStream.showText(FirstSubject);
		contentStream.endText();

		float x4 = 120;
		float y4 = 250;
		float width4 = 355;
		float height4 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x4, y4, width4, height4);
		contentStream.stroke();

		float innerX7 = x4 + 5;
		float innerY7 = y4 + 3;
		float innerWidth7 = 10;
		float innerHeight7 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerX7, innerY7, innerWidth7, innerHeight7);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX7 + 14, innerY7 + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerX8 = x4 + 60;
		float innerY8 = y4 + 3;
		float innerWidth8 = 10;
		float innerHeight8 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerX8, innerY8, innerWidth8, innerHeight8);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX8 + 14, innerY8 + 1);
		contentStream.showText("Avg Score");
		contentStream.endText();

		float innerX9 = x4 + 120;
		float innerY9 = y4 + 3;
		float innerWidth9 = 10;
		float innerHeight9 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerX9, innerY9, innerWidth9, innerHeight9);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX9 + 14, innerY9 + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerX10 = x4 + 165;
		float innerY10 = y4 + 3;
		float innerWidth10 = 10;
		float innerHeight10 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerX10, innerY10, innerWidth10, innerHeight10);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX10 + 14, innerY10 + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerX11 = x4 + 210;
		float innerY11 = y4 + 3;
		float innerWidth11 = 10;
		float innerHeight11 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 255, 0));
		contentStream.addRect(innerX11, innerY11, innerWidth11, innerHeight11);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX11 + 14, innerY11 + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		float innerX12 = x4 + 260;
		float innerY12 = y4 + 3;
		float innerWidth12 = 10;
		float innerHeight12 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(162, 32, 240));
		contentStream.addRect(innerX12, innerY12, innerWidth12, innerHeight12);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX12 + 14, innerY12 + 1);
		contentStream.showText("Rank 4");
		contentStream.endText();

		float innerX13 = x4 + 310;
		float innerY13 = y4 + 3;
		float innerWidth13 = 10;
		float innerHeight13 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(179, 216, 230));
		contentStream.addRect(innerX13, innerY13, innerWidth13, innerHeight13);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX13 + 14, innerY13 + 1);
		contentStream.showText("Rank 5");
		contentStream.endText();
		int initX = 50;
		int initY = 230;
		int cellHeight = 20;
		int cellWidth = 65;

		int colCount = 8;
		int rowCount = 2;

		int p1 = top5phyStudents.get(0).getScore();
		int p2 = top5phyStudents.get(1).getScore();
		int p3 = top5phyStudents.get(2).getScore();
		int p4 = top5phyStudents.get(3).getScore();
		int p5 = top5phyStudents.get(4).getScore();

		String[][] tableData = { { "Subject", "My Score", "Average", "Rank 1", "Rank 2", "Rank 3", "Rank 4", "Rank 5" },

				{ FirstSubject, phyScoredmarks, Float.toString((float) averagePhyScoredMarks), Integer.toString(p1),
						Integer.toString(p2), Integer.toString(p3), Integer.toString(p4), Integer.toString(p5) },

		};

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				String content = tableData[i][j];

				PDType1Font font = (i == 0) ? new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
						: new PDType1Font(Standard14Fonts.FontName.HELVETICA);

				contentStream.addRect(initX, initY, cellWidth, -cellHeight);

				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.newLineAtOffset(initX + 20, initY - cellHeight + 5);
				contentStream.showText(content);
				contentStream.endText();

				initX += cellWidth;
			}
			initX = 50;
			initY -= cellHeight;
		}

		contentStream.stroke();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));
		float xi = 20;
		float yi = 120;
		float widthi = 570;
		float heighti = 20;

		contentStream.addRect(xi, yi, widthi, heighti);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(xi + 5, yi + 5);
		contentStream.showText("Breakdown of Test Score");
		contentStream.endText();

	}

	private static void addContentTopage3Section1(String imageName, PDPage page3, PDPageContentStream contentStream,
			PDDocument document, int totalPhysicsScore, int physicsMarks, int attemptphysicsQuestions,
			int physicsScore1, int physicsScore2, String physicspercentage, List<StudentPhyScore> studentphyScores,
			int physicsScore3, List<StudentPositiveScore> top5Positive, String PositiveScore,
			List<StudentNegativeScore> top5Negative, String NegativeScore,String FirstSubject) throws IOException {

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(80, 750);
		contentStream.showText("Count of Questions");
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(270, 750);
		contentStream.showText("Marks of Questions");
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(480, 750);
		contentStream.showText("My Score");
		contentStream.endText();

		addPieChart2section1(document, contentStream, totalPhysicsScore,FirstSubject);
		addPieChart3section1(document, contentStream, totalPhysicsScore,FirstSubject);
		if (physicsMarks < 0) {

			float x = 450;
			float y = 680;
			float width = 100;
			float height = 20;

			contentStream.addRect(x, y, width, height);
			contentStream.setNonStrokingColor(new Color(255, 127, 127));
			contentStream.fill();

			contentStream.setStrokingColor(Color.BLACK);
			contentStream.addRect(x, y, width, height);
			contentStream.stroke();

			contentStream.beginText();
			contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
			contentStream.setNonStrokingColor(Color.BLACK);
			contentStream.newLineAtOffset(x + 10, y + 5);
			contentStream.showText("Negative Score");
			contentStream.endText();
		} else {
			addPieChart4Section1(document, contentStream, physicsMarks,FirstSubject);

		}
		int initX = 50;
		int initY = 600;
		int cellHeight = 20;
		int cellWidth = 60;

		int colCount = 9;
		int rowCount = 2;

		String targetImageName = imageName;

		int phyrank = getphyStudentRank(studentphyScores, targetImageName);

		String[][] tableData = {
				{ "Subject", "Total Q.", "Attempt Q.", "Correct", "Incorrect", "Raw Score", "Total(Max)", "Percent",
						"Rank" },

				{ FirstSubject, Integer.toString(totalPhysicsScore), Integer.toString(attemptphysicsQuestions),
						Integer.toString(physicsScore1), Integer.toString(physicsScore2),
						Integer.toString(physicsMarks), Integer.toString(totalPhysicsScore), physicspercentage,
						Integer.toString(phyrank) },

		};

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				String content = tableData[i][j];

				PDType1Font font = (i == 0) ? new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
						: new PDType1Font(Standard14Fonts.FontName.HELVETICA);

				contentStream.addRect(initX, initY, cellWidth, -cellHeight);

				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.newLineAtOffset(initX + 10, initY - cellHeight + 5);
				contentStream.showText(content);
				contentStream.endText();

				initX += cellWidth;
			}
			initX = 50;
			initY -= cellHeight;
		}

		contentStream.stroke();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));

		float x = 20;
		float y = 495;
		float width = 570;
		float height = 20;

		contentStream.addRect(x, y, width, height);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x + 5, y + 5);
		contentStream.showText("Analysis of Overall Test");
		contentStream.endText();

		addBarChart31Section1(contentStream, totalPhysicsScore, document, physicsScore1, physicsScore2, physicsScore3);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(240, 270);
		contentStream.showText(FirstSubject);
		contentStream.endText();

		float x2 = 140;
		float y2 = 240;
		float width2 = 355;
		float height2 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x2, y2, width2, height2);
		contentStream.stroke();

		float innerX = x2 + 30;
		float innerY = y2 + 3;
		float innerWidth = 10;
		float innerHeight = height2 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerX, innerY, innerWidth, innerHeight);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX + 14, innerY + 1);
		contentStream.showText("Total Questions");
		contentStream.endText();

		float innerX1 = x2 + 110;
		float innerY1 = y2 + 3;
		float innerWidth1 = 10;
		float innerHeight1 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerX1, innerY1, innerWidth1, innerHeight1);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX1 + 14, innerY1 + 1);
		contentStream.showText("Correct Questions");
		contentStream.endText();

		float innerX2 = x2 + 200;
		float innerY2 = y2 + 3;
		float innerWidth2 = 10;
		float innerHeight2 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerX2, innerY2, innerWidth2, innerHeight2);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX2 + 14, innerY2 + 1);
		contentStream.showText("Incorrect Questions");
		contentStream.endText();

		float innerX3 = x2 + 300;
		float innerY3 = y2 + 3;
		float innerWidth3 = 10;
		float innerHeight3 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerX3, innerY3, innerWidth3, innerHeight3);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX3 + 14, innerY3 + 1);
		contentStream.showText("Skipped");
		contentStream.endText();
		contentStream.setNonStrokingColor(new Color(255, 255, 0));

		float x3 = 20;
		float y3 = 200;
		float width3 = 570;
		float height3 = 20;

		contentStream.addRect(x3, y3, width3, height3);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 5, y3 + 5);
		contentStream.showText("Comparison of Positive Score");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 300, y3 + 5);
		contentStream.showText("Comparison of Negative Score");
		contentStream.endText();

		addBarChart3(contentStream, totalPhysicsScore, document, top5Positive, PositiveScore);
		addBarChart4(contentStream, totalPhysicsScore, document, top5Negative, NegativeScore);

		float x4 = 100;
		float y4 = 40;
		float width4 = 355;
		float height4 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x4, y4, width4, height4);
		contentStream.stroke();

		float innerXa = x4 + 30;
		float innerYa = y4 + 3;
		float innerWidtha = 10;
		float innerHeighta = height4 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerXa, innerYa, innerWidtha, innerHeighta);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXa + 14, innerYa + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerXb = x4 + 85;
		float innerYb = y4 + 3;
		float innerWidthb = 10;
		float innerHeightb = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerXb, innerYb, innerWidthb, innerHeightb);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXb + 14, innerYb + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerXc = x4 + 130;
		float innerYc = y4 + 3;
		float innerWidthc = 10;
		float innerHeightc = height4 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerXc, innerYc, innerWidthc, innerHeightc);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXc + 14, innerYc + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerXd = x4 + 175;
		float innerYd = y4 + 3;
		float innerWidthd = 10;
		float innerHeightd = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerXd, innerYd, innerWidthd, innerHeightd);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXd + 14, innerYd + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		float innerX4 = x4 + 220;
		float innerY4 = y4 + 3;
		float innerWidth4 = 10;
		float innerHeight4 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 255, 0));
		contentStream.addRect(innerX4, innerY4, innerWidth4, innerHeight4);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX4 + 14, innerY4 + 1);
		contentStream.showText("Rank 4");
		contentStream.endText();

		float innerX5 = x4 + 270;
		float innerY5 = y4 + 3;
		float innerWidth5 = 10;
		float innerHeight5 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(162, 32, 240));
		contentStream.addRect(innerX5, innerY5, innerWidth5, innerHeight5);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX5 + 14, innerY5 + 1);
		contentStream.showText("Rank 5");
		contentStream.endText();

	}

	@SuppressWarnings("unused")
	private static void addContentTopage4Section2(PDPageContentStream contentStream, PDDocument document,
			int physicsScore1, int mathsScore1, int physicsScore2, int mathsScore2,
			List<StudentPhysicsPositiveScore> top5PhysicsPositive, List<StudentMathPositiveScore> top5MathPositive,
			List<StudentPhysicsNegativeScore> top5PhysicsNegative, List<StudentMathNegativeScore> top5MathNegative,String FirstSubject,String SecondSubject)
			throws IOException {
		contentStream.setNonStrokingColor(new Color(0, 149, 193));

		float x3 = 20;
		float y3 = 750;
		float width3 = 570;
		float height3 = 20;

		contentStream.addRect(x3, y3, width3, height3);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 5, y3 + 5);
		contentStream.showText("Distribution of Section wise Positive Score");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 300, y3 + 5);
		contentStream.showText("Distribution of Section wise Negative Score");
		contentStream.endText();

		addPieChart5Section2(contentStream, document, physicsScore1, mathsScore1,FirstSubject,SecondSubject);
		addPieChart6Section2(contentStream, document, physicsScore2, mathsScore2,FirstSubject,SecondSubject);

		contentStream.setNonStrokingColor(new Color(0, 149, 193));

		float x4 = 20;
		float y4 = 580;
		float width4 = 570;
		float height4 = 20;

		contentStream.addRect(x4, y4, width4, height4);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x4 + 5, y4 + 5);
		contentStream.showText("Comparision of Section wise Positive Score");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x4 + 300, y4 + 5);
		contentStream.showText("Comparision of Section wise Negative Score");
		contentStream.endText();

		addBarChart5Section2(contentStream, document, physicsScore1, mathsScore1, top5PhysicsPositive,
				top5MathPositive);
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(100, 432);
		contentStream.showText(FirstSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(190, 432);
		contentStream.showText(SecondSubject);
		contentStream.endText();
		addBarChart6Section2(contentStream, document, physicsScore2, mathsScore2, top5PhysicsNegative,
				top5MathNegative);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(400, 432);
		contentStream.showText(FirstSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(500, 432);
		contentStream.showText(SecondSubject);
		contentStream.endText();

		float x41 = 120;
		float y41 = 400;
		float width41 = 355;
		float height41 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x41, y41, width41, height41);
		contentStream.stroke();

		float innerXa = x41 + 30;
		float innerYa = y41 + 3;
		float innerWidtha = 10;
		float innerHeighta = height41 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerXa, innerYa, innerWidtha, innerHeighta);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXa + 14, innerYa + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerXb = x41 + 85;
		float innerYb = y41 + 3;
		float innerWidthb = 10;
		float innerHeightb = height41 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerXb, innerYb, innerWidthb, innerHeightb);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXb + 14, innerYb + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerXc = x41 + 130;
		float innerYc = y41 + 3;
		float innerWidthc = 10;
		float innerHeightc = height41 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerXc, innerYc, innerWidthc, innerHeightc);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXc + 14, innerYc + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerXd = x41 + 180;
		float innerYd = y41 + 3;
		float innerWidthd = 10;
		float innerHeightd = height41 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerXd, innerYd, innerWidthd, innerHeightd);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXd + 14, innerYd + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		float innerX4 = x41 + 230;
		float innerY4 = y41 + 3;
		float innerWidth4 = 10;
		float innerHeight4 = height41 - 6;

		contentStream.setNonStrokingColor(new Color(255, 255, 0));
		contentStream.addRect(innerX4, innerY4, innerWidth4, innerHeight4);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX4 + 14, innerY4 + 1);
		contentStream.showText("Rank 4");
		contentStream.endText();

		float innerX5 = x41 + 280;
		float innerY5 = y41 + 3;
		float innerWidth5 = 10;
		float innerHeight5 = height41 - 6;

		contentStream.setNonStrokingColor(new Color(162, 32, 240));
		contentStream.addRect(innerX5, innerY5, innerWidth5, innerHeight5);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX5 + 14, innerY5 + 1);
		contentStream.showText("Rank 5");
		contentStream.endText();

		int initX = 50;
		int initY = 380;
		int cellHeight = 20;
		int cellWidth = 130;

		int colCount = 1;
		int rowCount = 4;

		String[][] tableData = { { "Subject" }, { "" }, { FirstSubject }, { SecondSubject },

		};

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				String content = tableData[i][j];

				PDType1Font font = (i == 0) ? new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
						: new PDType1Font(Standard14Fonts.FontName.HELVETICA);

				contentStream.addRect(initX, initY, cellWidth, -cellHeight);

				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.newLineAtOffset(initX + 40, initY - cellHeight + 5);
				contentStream.showText(content);
				contentStream.endText();

				initX += cellWidth;
			}
			initX = 50;
			initY -= cellHeight;
		}

		contentStream.stroke();
		int initX1 = 180;
		int initY1 = 380;
		int cellHeight1 = 20;
		int cellWidth1 = 30;

		int colCount1 = 6;
		int rowCount1 = 4;

		String[] headers = { "My Score", "Rank 1", "Rank 2", "Rank 3", "Rank 4", "Rank 5" };
		String[] subHeaders = { "+ve", "-ve", "+ve", "-ve", "+ve", "-ve", "+ve", "-ve", "+ve", "-ve", "+ve", "-ve" };

		int t1mp = top5MathPositive.get(0).getScore();
		int t2mp = top5MathPositive.get(1).getScore();
		int t3mp = top5MathPositive.get(2).getScore();
		int t4mp = top5MathPositive.get(3).getScore();
		int t5mp = top5MathPositive.get(4).getScore();

		int t1mn = top5MathNegative.get(0).getScore();
		int t2mn = top5MathNegative.get(1).getScore();
		int t3mn = top5MathNegative.get(2).getScore();
		int t4mn = top5MathNegative.get(3).getScore();
		int t5mn = top5MathNegative.get(4).getScore();

		int t1pp = top5PhysicsPositive.get(0).getScore();
		int t2pp = top5PhysicsPositive.get(1).getScore();
		int t3pp = top5PhysicsPositive.get(2).getScore();
		int t4pp = top5PhysicsPositive.get(3).getScore();
		int t5pp = top5PhysicsPositive.get(4).getScore();

		int t1pn = top5PhysicsNegative.get(0).getScore();
		int t2pn = top5PhysicsNegative.get(1).getScore();
		int t3pn = top5PhysicsNegative.get(2).getScore();
		int t4pn = top5PhysicsNegative.get(3).getScore();
		int t5pn = top5PhysicsNegative.get(4).getScore();

		String[][] tableData1 = {
				{ Integer.toString(physicsScore1), Integer.toString(physicsScore2), Integer.toString(t1pp),
						Integer.toString(t1pn), Integer.toString(t2pp), Integer.toString(t2pn), Integer.toString(t3pp),
						Integer.toString(t3pn), Integer.toString(t4pp), Integer.toString(t4pn), Integer.toString(t5pp),
						Integer.toString(t5pn) }, 
				{ Integer.toString(mathsScore1), Integer.toString(mathsScore2), Integer.toString(t1mp),
							Integer.toString(t1mn), Integer.toString(t2mp), Integer.toString(t2mn), Integer.toString(t3mp),
							Integer.toString(t3mn), Integer.toString(t4mp), Integer.toString(t4mn), Integer.toString(t5mp),
							Integer.toString(t5mn) },};

		for (int i = 0; i < headers.length; i++) {
			String content = headers[i];
			contentStream.addRect(initX1, initY1, cellWidth1 * 2, -cellHeight1);
			contentStream.beginText();
			contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 9);
			contentStream.setNonStrokingColor(Color.BLACK);
			contentStream.newLineAtOffset(initX1 + 10, initY1 - cellHeight1 + 5);
			contentStream.showText(content);
			contentStream.endText();
			initX1 += cellWidth1 * 2;
		}
		initX1 = 180;
		initY1 -= cellHeight1;

		for (int i = 0; i < subHeaders.length; i++) {
			String content = subHeaders[i];
			contentStream.addRect(initX1, initY1, cellWidth1, -cellHeight1);
			contentStream.beginText();
			contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 9);
			contentStream.setNonStrokingColor(Color.BLACK);
			contentStream.newLineAtOffset(initX1 + 10, initY1 - cellHeight1 + 5);
			contentStream.showText(content);
			contentStream.endText();
			initX1 += cellWidth1;
		}
		initX1 = 180;
		initY1 -= cellHeight1;

		for (int i = 0; i < tableData1.length; i++) {
			for (int j = 0; j < tableData1[i].length; j++) {
				String content = tableData1[i][j];
				contentStream.addRect(initX1, initY1, cellWidth1, -cellHeight1);
				contentStream.beginText();
				contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.newLineAtOffset(initX1 + 10, initY1 - cellHeight1 + 5);
				contentStream.showText(content);
				contentStream.endText();
				initX1 += cellWidth1;
			}
			initX1 = 180;
			initY1 -= cellHeight1;
		}

		contentStream.stroke();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart6Section2(PDPageContentStream contentStream, PDDocument document, int physicsScore2,
			int mathsScore2,String FirstSubject,String SecondSubject) throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, (physicsScore2));

		dataset.setValue(SecondSubject, (mathsScore2));

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));

		plot.setSectionPaint(SecondSubject, new Color(0, 255, 0));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart1.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 350, 600, 230, 140);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart5Section2(PDPageContentStream contentStream, PDDocument document, int physicsScore1,
			int mathsScore1,String FirstSubject,String SecondSubject) throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, (physicsScore1));

		dataset.setValue(SecondSubject, (mathsScore1));

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));

		plot.setSectionPaint(SecondSubject, new Color(0, 255, 0));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 50, 600, 230, 140);
	}

	private static void addBarChart6Section2(PDPageContentStream contentStream, PDDocument document, int physicsScore2,
			int mathsScore2, List<StudentPhysicsNegativeScore> top5PhysicsNegative,
			List<StudentMathNegativeScore> top5MathNegative) throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue((physicsScore2), "phy positive Marks", "");
		int topCount = 1;
		for (StudentPhysicsNegativeScore studentScore : top5PhysicsNegative) {
			dataset.addValue(studentScore.getScore(), "phy Top Marks " + topCount++, "");
		}

		dataset.addValue((mathsScore2), "math positive Marks", "");
		int topCount1 = 1;
		for (StudentMathNegativeScore studentScore : top5MathNegative) {
			dataset.addValue(studentScore.getScore(), "math Top Marks " + topCount1++, "");
		}

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));

		BarRenderer renderer = (BarRenderer) plot.getRenderer();

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(255, 255, 0));
		renderer.setSeriesPaint(5, new Color(162, 32, 240));

		renderer.setSeriesPaint(6, new Color(0, 0, 255));
		renderer.setSeriesPaint(7, new Color(255, 0, 0));

		renderer.setSeriesPaint(8, new Color(0, 255, 0));
		renderer.setSeriesPaint(9, new Color(255, 165, 0));
		renderer.setSeriesPaint(10, new Color(255, 255, 0));
		renderer.setSeriesPaint(11, new Color(162, 32, 240));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 10));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(0, 12);
		yAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 7));
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		Font tickLabelFont = new Font("Arial", Font.PLAIN, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(1);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 10));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 300, 430, 270, 145);
	}

	private static void addBarChart5Section2(PDPageContentStream contentStream, PDDocument document, int physicsScore1,
			int mathsScore1, List<StudentPhysicsPositiveScore> top5PhysicsPositive,
			List<StudentMathPositiveScore> top5MathPositive) throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue((physicsScore1), "phy positive Marks", "");
		int topCount = 1;
		for (StudentPhysicsPositiveScore studentScore : top5PhysicsPositive) {
			dataset.addValue(studentScore.getScore(), "phy Top Marks " + topCount++, "");
		}

		dataset.addValue((mathsScore1), "math positive Marks", "");
		int topCount1 = 1;
		for (StudentMathPositiveScore studentScore : top5MathPositive) {
			dataset.addValue(studentScore.getScore(), "math Top Marks " + topCount1++, "");
		}

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));

		BarRenderer renderer = (BarRenderer) plot.getRenderer();

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(255, 255, 0));
		renderer.setSeriesPaint(5, new Color(162, 32, 240));

		renderer.setSeriesPaint(6, new Color(0, 0, 255));
		renderer.setSeriesPaint(7, new Color(255, 0, 0));

		renderer.setSeriesPaint(8, new Color(0, 255, 0));
		renderer.setSeriesPaint(9, new Color(255, 165, 0));
		renderer.setSeriesPaint(10, new Color(255, 255, 0));
		renderer.setSeriesPaint(11, new Color(162, 32, 240));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 10));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(0, 12);
		yAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 7));
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		Font tickLabelFont = new Font("Arial", Font.PLAIN, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(1);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 10));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 15, 430, 270, 145);
	}

	private static void addBarChart2Section1(PDPageContentStream contentStream, String phyScoredmarks,
			PDDocument document, String maxMarks, int averagePhyScoredMarks, List<StudentPhyScore> top5phyStudents,
			int PhyMaxMarks) throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(Integer.parseInt(phyScoredmarks), "Scored Phy Marks", "");
		dataset.addValue((averagePhyScoredMarks), "Average Marks", "");

		int topCount = 1;
		for (StudentPhyScore studentScore : top5phyStudents) {
			dataset.addValue(studentScore.getScore(), "Top Marks " + topCount++, "");
		}

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));
		plot.getDomainAxis().setCategoryMargin(2.0);

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setItemMargin(0.5);

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(255, 255, 0));
		renderer.setSeriesPaint(5, new Color(162, 32, 240));
		renderer.setSeriesPaint(6, new Color(172, 216, 230));
		renderer.setSeriesPaint(7, new Color(0, 0, 255));
		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(8, new Color(255, 0, 0));

		renderer.setSeriesPaint(9, new Color(0, 255, 0));
		renderer.setSeriesPaint(10, new Color(255, 165, 0));
		renderer.setSeriesPaint(11, new Color(255, 255, 0));
		renderer.setSeriesPaint(12, new Color(162, 32, 240));
		renderer.setSeriesPaint(13, new Color(172, 216, 230));
		renderer.setSeriesPaint(14, new Color(0, 0, 255));
		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(15, new Color(255, 0, 0));

		renderer.setSeriesPaint(16, new Color(0, 255, 0));
		renderer.setSeriesPaint(17, new Color(255, 165, 0));
		renderer.setSeriesPaint(18, new Color(255, 255, 0));
		renderer.setSeriesPaint(19, new Color(162, 32, 240));
		renderer.setSeriesPaint(20, new Color(172, 216, 230));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 8));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(averagePhyScoredMarks, PhyMaxMarks);

		yAxis.setLabelFont(new Font("Arial", Font.BOLD, 8));

		Font tickLabelFont = new Font("Arial", Font.BOLD, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(2.0);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 8));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 10, 265, 580, 200);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart2section1(PDDocument document, PDPageContentStream contentStream,
			int totalPhysicsScore,String FirstSubject) throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, totalPhysicsScore);

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 30, 605, 210, 130);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart3section1(PDDocument document, PDPageContentStream contentStream,
			int totalPhysicsScore,String FirstSubject) throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, totalPhysicsScore);

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));


		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 225, 605, 210, 130);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart4Section1(PDDocument document, PDPageContentStream contentStream, int physicsMarks,String FirstSubject)
			throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, (physicsMarks));

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint("PHY", new Color(0, 0, 255));



		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart1.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 415, 605, 210, 130);
	}

	private static void addBarChart31Section1(PDPageContentStream contentStream, int totalPhysicsScore,
			PDDocument document, int physicsScore1, int physicsScore2, int physicsScore3) throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue((totalPhysicsScore), "Scored Phy Marks", "");
		dataset.addValue((physicsScore1), "phy score 1", "");
		dataset.addValue((physicsScore2), "phy score 2", "");
		dataset.addValue((physicsScore3), "phy score 3", "");

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));
		plot.getDomainAxis().setCategoryMargin(2.0);

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setItemMargin(0.5);

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(255, 255, 0));
		renderer.setSeriesPaint(5, new Color(162, 32, 240));
		renderer.setSeriesPaint(6, new Color(172, 216, 230));
		renderer.setSeriesPaint(7, new Color(0, 0, 255));

		renderer.setSeriesPaint(8, new Color(255, 0, 0));
		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 8));
		xAxis.setAxisLineVisible(false);

		double d = totalPhysicsScore;

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(0.0, d);

		yAxis.setLabelFont(new Font("Arial", Font.BOLD, 8));

		Font tickLabelFont = new Font("Arial", Font.BOLD, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(2.0);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 8));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 10, 280, 580, 200);
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Section -5
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	@SuppressWarnings("unused")
	private static void addContentToPage1Section5(PDDocument document, PDPage page, PDPageContentStream contentStream,
			String imageName, int totalStudents, String scoredMarks, String maxMarks, String percentage,
			int physicsMarks, int totalPhysicsScore, String physicspercentage, int chemistryMarks,
			int totalchemistryScore, String chemistrypercentage, int mathsMarks, int totalmathsScore,
			String mathspercentage, String fileNameWithoutExtension, int sansMarks, int totalsansScore,
			String sanspercentage, int engMarks, int totalengScore, String engpercentage
			,String FirstSubject,String SecondSubject,String ThirdSubject,String FourthSubject,String FifthSubject) throws IOException {

		float[] textColor1 = hexToRGB("#F5C300");

		String scoreReportBelowText = fileNameWithoutExtension;
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 26);
		contentStream.setNonStrokingColor(textColor1[0], textColor1[1], textColor1[2]);
		float textWidth1 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(scoreReportBelowText)
				/ 1000f * 40;
		float textX1 = 70;
		float textY1 = 612;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX1, textY1));
		contentStream.showText(scoreReportBelowText);
		contentStream.endText();

		float[] textColor = hexToRGB("#6978aa");

		String scoreReportText = "Score Report";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 46);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(scoreReportText) / 1000f
				* 40;
		float textX = 70;
		float textY = 640;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX, textY));
		contentStream.showText(scoreReportText);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 46);
		contentStream.setNonStrokingColor(textColor[0], textColor[1], textColor[2]);
		float shadowOffsetX = 1;
		float shadowOffsetY = 1;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX + shadowOffsetX, textY - shadowOffsetY));
		contentStream.showText(scoreReportText);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 505);
		contentStream.showText(Integer.toString(totalStudents));
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 475);
		contentStream.showText(scoredMarks + "/" + maxMarks + "(" + percentage + ")");
		contentStream.endText();

		/*
		 * contentStream.beginText(); contentStream.setFont(new
		 * PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		 * contentStream.setNonStrokingColor(0f, 0f, 0f);
		 * contentStream.newLineAtOffset(425, 390); contentStream.showText(maxMarks);
		 * contentStream.endText();
		 */
		/*
		 * contentStream.beginText(); contentStream.setFont(new
		 * PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		 * contentStream.setNonStrokingColor(0f, 0f, 0f);
		 * contentStream.newLineAtOffset(443, 390);
		 * contentStream.showText("("+percentage+")"); contentStream.endText();
		 */
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(200, 445);
		contentStream.showText(FirstSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 445);
		contentStream
				.showText(physicsMarks + "/" + Integer.toString(totalPhysicsScore) + "(" + physicspercentage + ")");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(200, 415);
		contentStream.showText(SecondSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 415);
		contentStream.showText(
				chemistryMarks + "/" + Integer.toString(totalchemistryScore) + "(" + chemistrypercentage + ")");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(200, 385);
		contentStream.showText(ThirdSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 385);
		contentStream.showText(mathsMarks + "/" + Integer.toString(totalmathsScore) + "(" + mathspercentage + ")");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(200, 355);
		contentStream.showText(FourthSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 355);
		contentStream.showText(sansMarks + "/" + Integer.toString(totalsansScore) + "(" + sanspercentage + ")");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(200, 325);
		contentStream.showText(FifthSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 325);
		contentStream.showText(engMarks + "/" + Integer.toString(totalengScore) + "(" + engpercentage + ")");
		contentStream.endText();

		String candidateIDText = "Candidate ID";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth2 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(candidateIDText) / 1000f
				* 40;
		float textX2 = 200;
		float textY2 = 565;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX2, textY2));
		contentStream.showText(candidateIDText);
		contentStream.endText();

		String candidateIDansText = "5000041";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth3 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(candidateIDansText)
				/ 1000f * 40;
		float textX3 = 400;
		float textY3 = 565;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX3, textY3));
		contentStream.showText(candidateIDansText);
		contentStream.endText();

		String assessmentDateText = "Assessment Date";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth4 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(assessmentDateText)
				/ 1000f * 40;
		float textX4 = 200;
		float textY4 = 535;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX4, textY4));
		contentStream.showText(assessmentDateText);
		contentStream.endText();

		String assessmentdateansText = "July 16,2024";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth5 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(assessmentdateansText)
				/ 1000f * 40;
		float textX5 = 400;
		float textY5 = 535;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX5, textY5));
		contentStream.showText(assessmentdateansText);
		contentStream.endText();

		String TotalcanText = "Total Candidates";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth6 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(TotalcanText) / 1000f
				* 40;
		float textX6 = 200;
		float textY6 = 505;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX6, textY6));
		contentStream.showText(TotalcanText);
		contentStream.endText();

		String RawscoreText = "My Raw Score";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth7 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(RawscoreText) / 1000f
				* 40;
		float textX7 = 200;
		float textY7 = 475;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX7, textY7));
		contentStream.showText(RawscoreText);
		contentStream.endText();

		String imagePath = "C:\\Users\\kusha\\OneDrive\\Desktop\\workspace\\pdfbuilder\\Picture1.jpg";
		PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath, document);
		float imageWidth = pdImage.getWidth();
		float imageHeight = pdImage.getHeight();
		float pageWidth = page.getMediaBox().getWidth();
		float pageHeight = page.getMediaBox().getHeight();
		float padding = 20;
		float x = padding;
		float y = 30;
		float scaledWidth = pageWidth - 2 * padding;
		float scaledHeight = imageHeight * (scaledWidth / imageWidth);
		contentStream.drawImage(pdImage, x, y, scaledWidth, scaledHeight);

		String text = "Demo Institution | Institución de demostración";
		float fontSize = 17;
		float textWidthinst = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD).getStringWidth(text) / 1000
				* fontSize;
		float textXinst = 40;
		float textYinst = y + scaledHeight - 200;

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 17);
		contentStream.setNonStrokingColor(1f, 1f, 1f);
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textXinst, textYinst));
		contentStream.showText(text);
		contentStream.endText();

		String imagePath2 = "C:\\Users\\kusha\\OneDrive\\Desktop\\workspace\\pdfbuilder\\Picture2.png";
		PDImageXObject pdImage2 = PDImageXObject.createFromFile(imagePath2, document);
		float imageWidth2 = pdImage2.getWidth();
		float imageHeight2 = pdImage2.getHeight();
		float pageWidth2 = page.getMediaBox().getWidth();
		float pageHeight2 = page.getMediaBox().getHeight();
		float padding2 = 20;
		float x2 = padding2;
		float y2 = 694;
		float scaledWidth2 = pageWidth2 - 2 * padding2;
		float scaledHeight2 = imageHeight2 * (scaledWidth2 / imageWidth2);
		contentStream.drawImage(pdImage2, x2, y2, scaledWidth2, scaledHeight2);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 23);
		contentStream.setNonStrokingColor(1f, 1f, 1f);
		float textXStudentName = x2 + 420;
		float textYStudentName = y2 + 20;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textXStudentName, textYStudentName));
		contentStream.showText(imageName);
		contentStream.endText();

	}

	private static void addContentToPage2Section5(PDPage page2, PDPageContentStream contentStream, String scoredMarks,
			PDDocument document, String maxMarks, double averageScoredMarks, List<StudentScore> top5Students,
			String correct, String incorrect, String unattempted, String phyScoredmarks, int averagePhyScoredMarks,
			List<StudentPhyScore> top5phyStudents, String chemScoredmarks, int PhyMaxMarks,
			List<StudentChemScore> top5chemStudents, int averageChemScoredMarks,
			List<StudentMathScore> top5mathStudents, String mathsScoredmarks, int averageMathScoredMarks,
			String sansScoredMarks, int averageSansScoredMarks, List<StudentSansScore> top5sansStudents,
			String engScoredmarks, int averageEngScoredMarks, List<StudentEngScore> top5engStudents,String FirstSubject,String SecondSubject,String ThirdSubject,String FourthSubject,String FifthSubject)
			throws IOException {

		contentStream.setNonStrokingColor(Color.YELLOW);

		float x = 20;
		float y = 750;
		float width = 570;
		float height = 20;

		contentStream.addRect(x, y, width, height);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x + 5, y + 5);
		contentStream.showText("Analysis of Overall Test");
		contentStream.endText();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));
		float x1 = 20;
		float y1 = 727;
		float width1 = 570;
		float height1 = 20;

		contentStream.addRect(x1, y1, width1, height1);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x1 + 5, y1 + 5);
		contentStream.showText("Comparison of Test Score");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 8);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(460, 715);
		contentStream.showText("My Correct, Incorrect &");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 8);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(448, 705);
		contentStream.showText("Unattempted Questions Count");
		contentStream.endText();

		addBarChart(contentStream, scoredMarks, document, maxMarks, averageScoredMarks, top5Students);
		addPieChart(contentStream, Integer.parseInt(correct), Integer.parseInt(incorrect),
				Integer.parseInt(unattempted), document);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(460, 550);
		contentStream.showText("Total Questions : " + maxMarks);
		contentStream.endText();

		int Attempt = Integer.parseInt(maxMarks) - Integer.parseInt(unattempted);
		float Attemptpercent = (Attempt / Integer.parseInt(maxMarks)) * 100;

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(469, 540);
		contentStream.showText("Attempt : " + Attemptpercent + " %");
		contentStream.endText();

		int Correct = Integer.parseInt(correct);
		int Max = Integer.parseInt(maxMarks);

		float temp = (Correct * 100) / Max;

		int Incorrect = Integer.parseInt(incorrect);
		float IncorrectPercent = (Incorrect * 100) / Max;

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(400, 515);
		contentStream.showText("Correct Ans : " + temp + "%" + " / " + "Incorrect Ans : " + IncorrectPercent + "%");
		contentStream.endText();

		float x2 = 40;
		float y2 = 510;
		float width2 = 355;
		float height2 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x2, y2, width2, height2);
		contentStream.stroke();

		float innerX = x2 + 5;
		float innerY = y2 + 3;
		float innerWidth = 10;
		float innerHeight = height2 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerX, innerY, innerWidth, innerHeight);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX + 14, innerY + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerX1 = x2 + 60;
		float innerY1 = y2 + 3;
		float innerWidth1 = 10;
		float innerHeight1 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerX1, innerY1, innerWidth1, innerHeight1);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX1 + 14, innerY1 + 1);
		contentStream.showText("Avg Score");
		contentStream.endText();

		float innerX2 = x2 + 120;
		float innerY2 = y2 + 3;
		float innerWidth2 = 10;
		float innerHeight2 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerX2, innerY2, innerWidth2, innerHeight2);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX2 + 14, innerY2 + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerX3 = x2 + 165;
		float innerY3 = y2 + 3;
		float innerWidth3 = 10;
		float innerHeight3 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerX3, innerY3, innerWidth3, innerHeight3);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX3 + 14, innerY3 + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerX4 = x2 + 210;
		float innerY4 = y2 + 3;
		float innerWidth4 = 10;
		float innerHeight4 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 255, 0));
		contentStream.addRect(innerX4, innerY4, innerWidth4, innerHeight4);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX4 + 14, innerY4 + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		float innerX5 = x2 + 260;
		float innerY5 = y2 + 3;
		float innerWidth5 = 10;
		float innerHeight5 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(160, 32, 240));
		contentStream.addRect(innerX5, innerY5, innerWidth5, innerHeight5);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX5 + 14, innerY5 + 1);
		contentStream.showText("Rank 4");
		contentStream.endText();

		float innerX6 = x2 + 310;
		float innerY6 = y2 + 3;
		float innerWidth6 = 10;
		float innerHeight6 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(173, 216, 230));
		contentStream.addRect(innerX6, innerY6, innerWidth6, innerHeight6);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX6 + 14, innerY6 + 1);
		contentStream.showText("Rank 5");
		contentStream.endText();

		float sectionX = 30;
		float sectionY = 495;

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(sectionX, sectionY);
		contentStream.showText("Test Toppers : ");

		StringBuilder topStudentsLine = new StringBuilder();
		for (int i = 0; i < Math.min(5, top5Students.size()); i++) {
			StudentScore student = top5Students.get(i);
			contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
			topStudentsLine.append(student.getName()).append(" (Score: ").append(student.getScore()).append(")");
			if (i < Math.min(5, top5Students.size()) - 1) {
				topStudentsLine.append(", ");
			}
		}
		contentStream.showText(topStudentsLine.toString());

		contentStream.endText();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));
		float x3 = 20;
		float y3 = 470;
		float width3 = 570;
		float height3 = 20;

		contentStream.addRect(x3, y3, width3, height3);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 5, y3 + 5);
		contentStream.showText("Comparison of Section wise Score");
		contentStream.endText();

		addBarChart2Section5(contentStream, phyScoredmarks, document, maxMarks, averagePhyScoredMarks, top5phyStudents,
				chemScoredmarks, PhyMaxMarks, top5chemStudents, averageChemScoredMarks, top5mathStudents,
				mathsScoredmarks, averageMathScoredMarks, sansScoredMarks, averageSansScoredMarks, top5sansStudents,
				engScoredmarks, averageEngScoredMarks, top5engStudents);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(120, 270);
		contentStream.showText(FirstSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(200, 270);
		contentStream.showText(SecondSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(300, 270);
		contentStream.showText(ThirdSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(400, 270);
		contentStream.showText(FourthSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(480, 270);
		contentStream.showText(FifthSubject);
		contentStream.endText();

		float x4 = 120;
		float y4 = 250;
		float width4 = 355;
		float height4 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x4, y4, width4, height4);
		contentStream.stroke();

		float innerX7 = x4 + 65;
		float innerY7 = y4 + 3;
		float innerWidth7 = 10;
		float innerHeight7 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerX7, innerY7, innerWidth7, innerHeight7);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX7 + 14, innerY7 + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerX8 = x4 + 120;
		float innerY8 = y4 + 3;
		float innerWidth8 = 10;
		float innerHeight8 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerX8, innerY8, innerWidth8, innerHeight8);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX8 + 14, innerY8 + 1);
		contentStream.showText("Avg Score");
		contentStream.endText();

		float innerX9 = x4 + 180;
		float innerY9 = y4 + 3;
		float innerWidth9 = 10;
		float innerHeight9 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerX9, innerY9, innerWidth9, innerHeight9);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX9 + 14, innerY9 + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerX10 = x4 + 227;
		float innerY10 = y4 + 3;
		float innerWidth10 = 10;
		float innerHeight10 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerX10, innerY10, innerWidth10, innerHeight10);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX10 + 14, innerY10 + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerX11 = x4 + 271;
		float innerY11 = y4 + 3;
		float innerWidth11 = 10;
		float innerHeight11 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 255, 0));
		contentStream.addRect(innerX11, innerY11, innerWidth11, innerHeight11);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX11 + 14, innerY11 + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		int initX = 50;
		int initY = 230;
		int cellHeight = 20;
		int cellWidth = 65;

		int colCount = 8;
		int rowCount = 6;
		int m1 = top5mathStudents.get(0).getScore();
		int m2 = top5mathStudents.get(1).getScore();
		int m3 = top5mathStudents.get(2).getScore();
		int m4 = top5mathStudents.get(3).getScore();
		int m5 = top5mathStudents.get(4).getScore();

		int p1 = top5phyStudents.get(0).getScore();
		int p2 = top5phyStudents.get(1).getScore();
		int p3 = top5phyStudents.get(2).getScore();
		int p4 = top5phyStudents.get(3).getScore();
		int p5 = top5phyStudents.get(4).getScore();

		int c1 = top5chemStudents.get(0).getScore();
		int c2 = top5chemStudents.get(1).getScore();
		int c3 = top5chemStudents.get(2).getScore();
		int c4 = top5chemStudents.get(3).getScore();
		int c5 = top5chemStudents.get(4).getScore();

		int s1 = top5sansStudents.get(0).getScore();
		int s2 = top5sansStudents.get(1).getScore();
		int s3 = top5sansStudents.get(2).getScore();
		int s4 = top5sansStudents.get(3).getScore();
		int s5 = top5sansStudents.get(4).getScore();

		int e1 = top5engStudents.get(0).getScore();
		int e2 = top5engStudents.get(1).getScore();
		int e3 = top5engStudents.get(2).getScore();
		int e4 = top5engStudents.get(3).getScore();
		int e5 = top5engStudents.get(4).getScore();

		String[][] tableData = { { "Subject", "My Score", "Average", "Rank 1", "Rank 2", "Rank 3", "Rank 4", "Rank 5" },
				
				{ FirstSubject, phyScoredmarks, Float.toString((float) averagePhyScoredMarks), Integer.toString(p1),
						Integer.toString(p2), Integer.toString(p3), Integer.toString(p4), Integer.toString(p5) },
				{ SecondSubject, chemScoredmarks, Float.toString((float) averageChemScoredMarks), Integer.toString(c1),
						Integer.toString(c2), Integer.toString(c3), Integer.toString(c4), Integer.toString(c5) },
				{ ThirdSubject, mathsScoredmarks, Float.toString((float) averageMathScoredMarks), Integer.toString(m1),
							Integer.toString(m2), Integer.toString(m3), Integer.toString(m4), Integer.toString(m5) },
				{ FourthSubject, sansScoredMarks, Float.toString((float) averageSansScoredMarks), Integer.toString(s1),
						Integer.toString(s2), Integer.toString(s3), Integer.toString(s4), Integer.toString(s5) },
				{ FifthSubject, engScoredmarks, Float.toString((float) averageEngScoredMarks), Integer.toString(e1),
						Integer.toString(e2), Integer.toString(e3), Integer.toString(e4), Integer.toString(e5) },

		};

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				String content = tableData[i][j];

				PDType1Font font = (i == 0) ? new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
						: new PDType1Font(Standard14Fonts.FontName.HELVETICA);

				contentStream.addRect(initX, initY, cellWidth, -cellHeight);

				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.newLineAtOffset(initX + 20, initY - cellHeight + 5);
				contentStream.showText(content);
				contentStream.endText();

				initX += cellWidth;
			}
			initX = 50;
			initY -= cellHeight;
		}

		contentStream.stroke();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));
		float xi = 20;
		float yi = 80;
		float widthi = 570;
		float heighti = 20;

		contentStream.addRect(xi, yi, widthi, heighti);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(xi + 5, yi + 5);
		contentStream.showText("Breakdown of Test Score");
		contentStream.endText();

	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Functions
	 * 
	 * 
	 * 
	 * 
	 */

	private static void addBarChart2Section5(PDPageContentStream contentStream, String phyScoredmarks,
			PDDocument document, String maxMarks, int averagePhyScoredMarks, List<StudentPhyScore> top5phyStudents,
			String chemScoredmarks, int PhyMaxMarks, List<StudentChemScore> top5chemStudents,
			int averageChemScoredMarks, List<StudentMathScore> top5mathStudents, String mathsScoredmarks,
			int averageMathScoredMarks, String sansScoredmarks, int averageSansScoredMarks,
			List<StudentSansScore> top5sansStudents, String engScoredmarks, int averageEngScoredMarks,
			List<StudentEngScore> top5engStudents) throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(Integer.parseInt(phyScoredmarks), "Scored Phy Marks", "");
		dataset.addValue((averagePhyScoredMarks), "Average Marks", "");

		int topCount = 1;
		for (StudentPhyScore studentScore : top5phyStudents) {
			if (topCount > 3)
				break;
			dataset.addValue(studentScore.getScore(), "Top Marks " + topCount++, "");
		}

		dataset.addValue(Integer.parseInt(chemScoredmarks), "Scored Chem Marks", "");
		dataset.addValue((averageChemScoredMarks), "Average Chem Marks", "");
		int topCount0 = 1;
		for (StudentChemScore studentScore : top5chemStudents) {
			if (topCount0 > 3)
				break;
			dataset.addValue(studentScore.getScore(), "Top Chem Marks " + topCount0++, "");
		}

		dataset.addValue(Integer.parseInt(mathsScoredmarks), "Scored math Marks", "");
		dataset.addValue((averageMathScoredMarks), "Average Math Marks", "");
		int topCount1 = 1;
		for (StudentMathScore studentScore : top5mathStudents) {
			if (topCount1 > 3)
				break;
			dataset.addValue(studentScore.getScore(), "Top Math Marks " + topCount1++, "");
		}

		dataset.addValue(Integer.parseInt(sansScoredmarks), "Scored sans Marks", "");
		dataset.addValue((averageSansScoredMarks), "Average Sans Marks", "");
		int topCount2 = 1;
		for (StudentSansScore studentScore : top5sansStudents) {
			if (topCount2 > 3)
				break;
			dataset.addValue(studentScore.getScore(), "Top Sans Marks " + topCount2++, "");
		}

		dataset.addValue(Integer.parseInt(engScoredmarks), "Scored eng Marks", "");
		dataset.addValue((averageEngScoredMarks), "Average Eng Marks", "");
		int topCount3 = 1;
		for (StudentEngScore studentScore : top5engStudents) {
			if (topCount3 > 3)
				break;
			dataset.addValue(studentScore.getScore(), "Top Eng Marks " + topCount3++, "");
		}

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));
		plot.getDomainAxis().setCategoryMargin(2.0);

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setItemMargin(0.5);

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(255, 255, 0));

		renderer.setSeriesPaint(5, new Color(0, 0, 255));
		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(6, new Color(255, 0, 0));

		renderer.setSeriesPaint(7, new Color(0, 255, 0));
		renderer.setSeriesPaint(8, new Color(255, 165, 0));
		renderer.setSeriesPaint(9, new Color(255, 255, 0));

		renderer.setSeriesPaint(10, new Color(0, 0, 255));
		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(11, new Color(255, 0, 0));

		renderer.setSeriesPaint(12, new Color(0, 255, 0));
		renderer.setSeriesPaint(13, new Color(255, 165, 0));
		renderer.setSeriesPaint(14, new Color(255, 255, 0));

		renderer.setSeriesPaint(15, new Color(0, 0, 255));
		renderer.setSeriesPaint(16, new Color(255, 0, 0));

		renderer.setSeriesPaint(17, new Color(0, 255, 0));
		renderer.setSeriesPaint(18, new Color(255, 165, 0));
		renderer.setSeriesPaint(19, new Color(255, 255, 0));
		renderer.setSeriesPaint(20, new Color(0, 0, 255));
		renderer.setSeriesPaint(21, new Color(255, 0, 0));

		renderer.setSeriesPaint(22, new Color(0, 255, 0));
		renderer.setSeriesPaint(23, new Color(255, 165, 0));
		renderer.setSeriesPaint(24, new Color(255, 255, 0));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 8));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(averagePhyScoredMarks, PhyMaxMarks);

		yAxis.setLabelFont(new Font("Arial", Font.BOLD, 8));

		Font tickLabelFont = new Font("Arial", Font.BOLD, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(2.0);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 8));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 10, 265, 580, 200);
	}

	private static void addContentTopage3Section5(String imageName, PDPage page3, PDPageContentStream contentStream,
			PDDocument document, int totalPhysicsScore, int totalchemistryScore, int totalmathsScore, int physicsMarks,
			int chemistryMarks, int mathsMarks, int attemptmathQuestions, int mathsScore1, int mathsScore2,
			String mathspercentage, int attemptphysicsQuestions, int physicsScore1, int physicsScore2,
			String physicspercentage, int attemptchemistryQuestions, int chemistryScore1, int chemistryScore2,
			String chemistrypercentage, List<StudentMathScore> studentmathScores,
			List<StudentPhyScore> studentphyScores, List<StudentChemScore> studentchemScores, int physicsScore3,
			int chemScore3, int mathsScore3, List<StudentPositiveScore> top5Positive, String PositiveScore,
			List<StudentNegativeScore> top5Negative, String NegativeScore, int totalsansScore, int totalengScore,
			int sansMarks, int engMarks, int attemptsansQuestions, int sansScore1, int sansScore2, int sansScore3,
			String sanspercentage, int attemptengQuestions, int engScore1, int engScore2, int engScore3,
			String engpercentage, List<StudentSansScore> studentsansScores, List<StudentEngScore> studentengScores,
			String maxMarks,String FirstSubject,String SecondSubject,String ThirdSubject,String FourthSubject,String FifthSubject) throws IOException {

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(80, 750);
		contentStream.showText("Count of Questions");
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(270, 750);
		contentStream.showText("Marks of Questions");
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(480, 750);
		contentStream.showText("My Score");
		contentStream.endText();

		addPieChart2Section5(document, contentStream, totalPhysicsScore, totalchemistryScore, totalmathsScore,
				totalsansScore, totalengScore,FirstSubject,SecondSubject,ThirdSubject,FourthSubject,FifthSubject);
		addPieChart3Section5(document, contentStream, totalPhysicsScore, totalchemistryScore, totalmathsScore,
				totalsansScore, totalengScore,FirstSubject,SecondSubject,ThirdSubject,FourthSubject,FifthSubject);
		if (physicsMarks < 0 && chemistryMarks < 0 && mathsMarks < 0) {

			float x = 450;
			float y = 680;
			float width = 100;
			float height = 20;

			contentStream.addRect(x, y, width, height);
			contentStream.setNonStrokingColor(new Color(255, 127, 127));
			contentStream.fill();

			contentStream.setStrokingColor(Color.BLACK);
			contentStream.addRect(x, y, width, height);
			contentStream.stroke();

			contentStream.beginText();
			contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
			contentStream.setNonStrokingColor(Color.BLACK);
			contentStream.newLineAtOffset(x + 10, y + 5);
			contentStream.showText("Negative Score");
			contentStream.endText();
		} else {
			addPieChart4Section5(document, contentStream, physicsMarks, chemistryMarks, mathsMarks, sansMarks,
					engMarks,FirstSubject,SecondSubject,ThirdSubject,FourthSubject,FifthSubject);

		}
		int initX = 50;
		int initY = 600;
		int cellHeight = 20;
		int cellWidth = 60;

		int colCount = 9;
		int rowCount = 6;

		String targetImageName = imageName;
		int mathrank = getStudentRank(studentmathScores, targetImageName);
		int phyrank = getphyStudentRank(studentphyScores, targetImageName);
		int chemrank = getchemtudentRank(studentchemScores, targetImageName);
		int sansrank = getsanstudentRank(studentsansScores, targetImageName);
		int engrank = getEngStudentRank(studentengScores, targetImageName);

		String[][] tableData = {
				{ "Subject", "Total Q.", "Attempt Q.", "Correct", "Incorrect", "Raw Score", "Total(Max)", "Percent",
						"Rank" },
				
				{ FirstSubject, Integer.toString(totalPhysicsScore), Integer.toString(attemptphysicsQuestions),
						Integer.toString(physicsScore1), Integer.toString(physicsScore2),
						Integer.toString(physicsMarks), Integer.toString(totalPhysicsScore), physicspercentage,
						Integer.toString(phyrank) },
				{ SecondSubject, Integer.toString(totalchemistryScore), Integer.toString(attemptchemistryQuestions),
						Integer.toString(chemistryScore1), Integer.toString(chemistryScore2),
						Integer.toString(chemistryMarks), Integer.toString(totalchemistryScore), chemistrypercentage,
						Integer.toString(chemrank) },
				{ ThirdSubject, Integer.toString(totalmathsScore), Integer.toString(attemptmathQuestions),
							Integer.toString(mathsScore1), Integer.toString(mathsScore2), Integer.toString(mathsMarks),
							Integer.toString(totalmathsScore), mathspercentage, Integer.toString(mathrank) },
				{ FourthSubject, Integer.toString(totalsansScore), Integer.toString(attemptsansQuestions),
						Integer.toString(sansScore1), Integer.toString(sansScore2), Integer.toString(sansMarks),
						Integer.toString(totalsansScore), sanspercentage, Integer.toString(sansrank) },
				{FifthSubject , Integer.toString(totalengScore), Integer.toString(attemptengQuestions),
						Integer.toString(engScore1), Integer.toString(engScore2), Integer.toString(engMarks),
						Integer.toString(totalengScore), engpercentage, Integer.toString(engrank) },

		};

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				String content = tableData[i][j];

				PDType1Font font = (i == 0) ? new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
						: new PDType1Font(Standard14Fonts.FontName.HELVETICA);

				contentStream.addRect(initX, initY, cellWidth, -cellHeight);

				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.newLineAtOffset(initX + 10, initY - cellHeight + 5);
				contentStream.showText(content);
				contentStream.endText();

				initX += cellWidth;
			}
			initX = 50;
			initY -= cellHeight;
		}

		contentStream.stroke();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));

		float x = 20;
		float y = 440;
		float width = 570;
		float height = 20;

		contentStream.addRect(x, y, width, height);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x + 5, y + 5);
		contentStream.showText("Analysis of Overall Test");
		contentStream.endText();

		addBarChart31Section5(contentStream, totalPhysicsScore, totalchemistryScore, totalmathsScore, document,
				physicsScore1, physicsScore2, physicsScore3, chemistryScore1, chemistryScore2, chemScore3, mathsScore1,
				mathsScore2, mathsScore3, totalsansScore, totalengScore, sansScore1, sansScore2, sansScore3, engScore1,
				engScore2, engScore3);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(100, 270);
		contentStream.showText(FirstSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(200, 270);
		contentStream.showText(SecondSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(300, 270);
		contentStream.showText(ThirdSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(400, 270);
		contentStream.showText(FourthSubject);
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(500, 270);
		contentStream.showText(FifthSubject);
		contentStream.endText();

		float x2 = 140;
		float y2 = 240;
		float width2 = 355;
		float height2 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x2, y2, width2, height2);
		contentStream.stroke();

		float innerX = x2 + 30;
		float innerY = y2 + 3;
		float innerWidth = 10;
		float innerHeight = height2 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerX, innerY, innerWidth, innerHeight);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX + 14, innerY + 1);
		contentStream.showText("Total Questions");
		contentStream.endText();

		float innerX1 = x2 + 110;
		float innerY1 = y2 + 3;
		float innerWidth1 = 10;
		float innerHeight1 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerX1, innerY1, innerWidth1, innerHeight1);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX1 + 14, innerY1 + 1);
		contentStream.showText("Correct Questions");
		contentStream.endText();

		float innerX2 = x2 + 200;
		float innerY2 = y2 + 3;
		float innerWidth2 = 10;
		float innerHeight2 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerX2, innerY2, innerWidth2, innerHeight2);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX2 + 14, innerY2 + 1);
		contentStream.showText("Incorrect Questions");
		contentStream.endText();

		float innerX3 = x2 + 300;
		float innerY3 = y2 + 3;
		float innerWidth3 = 10;
		float innerHeight3 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerX3, innerY3, innerWidth3, innerHeight3);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX3 + 14, innerY3 + 1);
		contentStream.showText("Skipped");
		contentStream.endText();
		contentStream.setNonStrokingColor(new Color(255, 255, 0));

		float x3 = 20;
		float y3 = 200;
		float width3 = 570;
		float height3 = 20;

		contentStream.addRect(x3, y3, width3, height3);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 5, y3 + 5);
		contentStream.showText("Comparison of Positive Score");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 300, y3 + 5);
		contentStream.showText("Comparison of Negative Score");
		contentStream.endText();

		addBarChart3Section5(contentStream, totalPhysicsScore, document, top5Positive, PositiveScore, maxMarks);
		addBarChart4Section5(contentStream, totalPhysicsScore, document, top5Negative, NegativeScore, maxMarks);

		float x4 = 100;
		float y4 = 40;
		float width4 = 355;
		float height4 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x4, y4, width4, height4);
		contentStream.stroke();

		float innerXa = x4 + 30;
		float innerYa = y4 + 3;
		float innerWidtha = 10;
		float innerHeighta = height4 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerXa, innerYa, innerWidtha, innerHeighta);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXa + 14, innerYa + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerXb = x4 + 85;
		float innerYb = y4 + 3;
		float innerWidthb = 10;
		float innerHeightb = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerXb, innerYb, innerWidthb, innerHeightb);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXb + 14, innerYb + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerXc = x4 + 130;
		float innerYc = y4 + 3;
		float innerWidthc = 10;
		float innerHeightc = height4 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerXc, innerYc, innerWidthc, innerHeightc);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXc + 14, innerYc + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerXd = x4 + 175;
		float innerYd = y4 + 3;
		float innerWidthd = 10;
		float innerHeightd = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerXd, innerYd, innerWidthd, innerHeightd);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXd + 14, innerYd + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		float innerX4 = x4 + 220;
		float innerY4 = y4 + 3;
		float innerWidth4 = 10;
		float innerHeight4 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 255, 0));
		contentStream.addRect(innerX4, innerY4, innerWidth4, innerHeight4);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX4 + 14, innerY4 + 1);
		contentStream.showText("Rank 4");
		contentStream.endText();

		float innerX5 = x4 + 270;
		float innerY5 = y4 + 3;
		float innerWidth5 = 10;
		float innerHeight5 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(162, 32, 240));
		contentStream.addRect(innerX5, innerY5, innerWidth5, innerHeight5);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX5 + 14, innerY5 + 1);
		contentStream.showText("Rank 5");
		contentStream.endText();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart2Section5(PDDocument document, PDPageContentStream contentStream,
			int totalPhysicsScore, int totalchemistryScore, int totalmathsScore, int totalsansScore, int totalengScore,String FirstSubject,
			String SecondSubject,String ThirdSubject,String FourthSubject,String FifthSubject)
			throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, totalPhysicsScore);
		dataset.setValue(SecondSubject, totalchemistryScore);
		dataset.setValue(ThirdSubject, totalmathsScore);
		dataset.setValue(FourthSubject, totalsansScore);
		dataset.setValue(FifthSubject, totalengScore);

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));
		plot.setSectionPaint(SecondSubject, new Color(255, 0, 0));
		plot.setSectionPaint(ThirdSubject, new Color(0, 255, 0));
		plot.setSectionPaint(FourthSubject, new Color(255, 165, 0));
		plot.setSectionPaint(FifthSubject, new Color(255, 255, 0));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 30, 605, 210, 130);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart3Section5(PDDocument document, PDPageContentStream contentStream,
			int totalPhysicsScore, int totalchemistryScore, int totalmathsScore, int totalsansScore, int totalengScore
			,String FirstSubject,String SecondSubject,String ThirdSubject,String FourthSubject,String FifthSubject)
			throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, totalPhysicsScore);
		dataset.setValue(SecondSubject, totalchemistryScore);
		dataset.setValue(ThirdSubject, totalmathsScore);
		dataset.setValue(FourthSubject, totalsansScore);
		dataset.setValue(FifthSubject, totalengScore);

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));
		plot.setSectionPaint(SecondSubject, new Color(255, 0, 0));
		plot.setSectionPaint(ThirdSubject, new Color(0, 255, 0));
		plot.setSectionPaint(FourthSubject, new Color(255, 165, 0));
		plot.setSectionPaint(FifthSubject, new Color(255, 255, 0));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 225, 605, 210, 130);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart4Section5(PDDocument document, PDPageContentStream contentStream, int physicsMarks,
			int chemistryMarks, int mathsMarks, int sansMarks, int engMarks,
			String FirstSubject,String SecondSubject,String ThirdSubject,String FourthSubject,String FifthSubject) throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, (physicsMarks));
		dataset.setValue(SecondSubject, (chemistryMarks));
		dataset.setValue(ThirdSubject, (mathsMarks));
		dataset.setValue(FourthSubject, (sansMarks));
		dataset.setValue(FifthSubject, (engMarks));

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));
		plot.setSectionPaint(SecondSubject, new Color(255, 0, 0));
		plot.setSectionPaint(ThirdSubject, new Color(0, 255, 0));
		plot.setSectionPaint(FourthSubject, new Color(255, 165, 0));
		plot.setSectionPaint(FifthSubject, new Color(255, 255, 0));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart1.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 415, 605, 210, 130);
	}

	private static void addBarChart31Section5(PDPageContentStream contentStream, int totalPhysicsScore,
			int totalChemistryScore, int totalmathsScore, PDDocument document, int physicsScore1, int physicsScore2,
			int physicsScore3, int chemScore1, int chemScore2, int chemScore3, int mathsScore1, int mathsScore2,
			int mathsScore3, int totalsansScore, int totalengScore, int sansScore1, int sansScore2, int sansScore3,
			int engScore1, int engScore2, int engScore3) throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue((totalPhysicsScore), "Scored Phy Marks", "");
		dataset.addValue((physicsScore1), "phy score 1", "");
		dataset.addValue((physicsScore2), "phy score 2", "");
		dataset.addValue((physicsScore3), "phy score 3", "");

		dataset.addValue((totalChemistryScore), "Scored chem Marks", "");
		dataset.addValue((chemScore1), "chem  score 1", "");
		dataset.addValue((chemScore2), "chem score 2", "");
		dataset.addValue((chemScore3), "chem  score 3", "");

		dataset.addValue((totalmathsScore), "Scored math Marks", "");
		dataset.addValue((mathsScore1), "math score 1", "");
		dataset.addValue((mathsScore2), "math score 2", "");
		dataset.addValue((mathsScore3), "math score 3", "");

		dataset.addValue((totalsansScore), "Scored sans Marks", "");
		dataset.addValue((sansScore1), "sans score 1", "");
		dataset.addValue((sansScore2), "sans score 2", "");
		dataset.addValue((sansScore3), "sans score 3", "");

		dataset.addValue((totalengScore), "Scored eng Marks", "");
		dataset.addValue((engScore1), "eng score 1", "");
		dataset.addValue((engScore2), "eng score 2", "");
		dataset.addValue((engScore3), "eng score 3", "");

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));
		plot.getDomainAxis().setCategoryMargin(2.0);

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setItemMargin(0.5);

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(0, 0, 255));
		renderer.setSeriesPaint(5, new Color(255, 0, 0));
		renderer.setSeriesPaint(6, new Color(0, 255, 0));
		renderer.setSeriesPaint(7, new Color(255, 165, 0));

		renderer.setSeriesPaint(8, new Color(0, 0, 255));
		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(9, new Color(255, 0, 0));

		renderer.setSeriesPaint(10, new Color(0, 255, 0));
		renderer.setSeriesPaint(11, new Color(255, 165, 0));
		renderer.setSeriesPaint(12, new Color(0, 0, 255));
		renderer.setSeriesPaint(13, new Color(255, 0, 0));
		renderer.setSeriesPaint(14, new Color(0, 255, 0));
		renderer.setSeriesPaint(15, new Color(255, 165, 0));
		renderer.setSeriesPaint(16, new Color(0, 0, 255));
		renderer.setSeriesPaint(17, new Color(255, 0, 0));
		renderer.setSeriesPaint(18, new Color(0, 255, 0));
		renderer.setSeriesPaint(19, new Color(255, 165, 0));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 8));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(0.0, 10);

		yAxis.setLabelFont(new Font("Arial", Font.BOLD, 8));

		Font tickLabelFont = new Font("Arial", Font.BOLD, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(2.0);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 8));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 10, 280, 580, 150);
	}

	private static void addBarChart4Section5(PDPageContentStream contentStream, int totalPhysicsScore,
			PDDocument document, List<StudentNegativeScore> top5Negative, String NegativeScore, String maxMarks)
			throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(Integer.parseInt(NegativeScore), "Scored Marks", "");

		int topCount = 1;
		for (StudentNegativeScore studentScore : top5Negative) {
			dataset.addValue(studentScore.getScore(), "Top Marks " + topCount++, "");
		}

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));

		BarRenderer renderer = (BarRenderer) plot.getRenderer();

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(255, 255, 0));
		renderer.setSeriesPaint(5, new Color(162, 32, 240));
		renderer.setSeriesPaint(6, new Color(172, 216, 230));
		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 10));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(0, Integer.parseInt(maxMarks));
		yAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 7));
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		Font tickLabelFont = new Font("Arial", Font.PLAIN, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(1);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 10));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 300, 50, 270, 145);
	}

	private static void addBarChart3Section5(PDPageContentStream contentStream, int totalPhysicsScore,
			PDDocument document, List<StudentPositiveScore> top5Positive, String positiveScore, String maxMarks)
			throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(Integer.parseInt(positiveScore), "Scored Marks", "");

		int topCount = 1;
		for (StudentPositiveScore studentScore : top5Positive) {
			dataset.addValue(studentScore.getScore(), "Top Marks " + topCount++, "");
		}

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));

		BarRenderer renderer = (BarRenderer) plot.getRenderer();

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(255, 255, 0));
		renderer.setSeriesPaint(5, new Color(162, 32, 240));
		renderer.setSeriesPaint(6, new Color(172, 216, 230));
		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 10));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(0, Integer.parseInt(maxMarks));
		yAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 7));
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		Font tickLabelFont = new Font("Arial", Font.PLAIN, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(1);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 10));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 15, 50, 270, 145);
	}

	private static void addContentTopage4Section5(PDPageContentStream contentStream, PDDocument document,
			int physicsScore1, int chemistryScore1, int mathsScore1, int physicsScore2, int chemistryScore2,
			int mathsScore2, List<StudentPhysicsPositiveScore> top5PhysicsPositive,
			List<StudentChemPositiveScore> top5ChemPositive, List<StudentMathPositiveScore> top5MathPositive,
			List<StudentPhysicsNegativeScore> top5PhysicsNegative, List<StudentChemNegativeScore> top5ChemNegative,
			List<StudentMathNegativeScore> top5MathNegative, int sansScore1, int engScore1, int sansScore2,
			int engScore2, List<StudentSansPositiveScore> top5SansPositive,
			List<StudentEngPositiveScore> top5EngPositive, List<StudentSansNegativeScore> top5SansNegative,
			List<StudentEngNegativeScore> top5EngNegative,String FirstSubject,String SecondSubject,String ThirdSubject,String FourthSubject,String FifthSubject) throws IOException {
		contentStream.setNonStrokingColor(new Color(0, 149, 193));

		float x3 = 20;
		float y3 = 750;
		float width3 = 570;
		float height3 = 20;

		contentStream.addRect(x3, y3, width3, height3);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 5, y3 + 5);
		contentStream.showText("Distribution of Section wise Positive Score");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 300, y3 + 5);
		contentStream.showText("Distribution of Section wise Negative Score");
		contentStream.endText();

		addPieChart5Section5(contentStream, document, physicsScore1, chemistryScore1, mathsScore1, sansScore1,
				engScore1, FirstSubject, SecondSubject, ThirdSubject, FourthSubject, FifthSubject);
		addPieChart6Section5(contentStream, document, physicsScore2, chemistryScore2, mathsScore2, sansScore2,
				engScore2, FirstSubject, SecondSubject, ThirdSubject, FourthSubject, FifthSubject);

		contentStream.setNonStrokingColor(new Color(0, 149, 193));

		float x4 = 20;
		float y4 = 580;
		float width4 = 570;
		float height4 = 20;

		contentStream.addRect(x4, y4, width4, height4);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x4 + 5, y4 + 5);
		contentStream.showText("Comparision of Section wise Positive Score");
		contentStream.endText();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));

		float x41 = 20;
		float y41 = 330;
		float width41 = 570;
		float height41 = 20;

		contentStream.addRect(x41, y41, width41, height41);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x41 + 5, y41 + 5);
		contentStream.showText("Comparision of Section wise Negative Score");
		contentStream.endText();

		addBarChart5Section5(contentStream, document, physicsScore1, chemistryScore1, mathsScore1, top5PhysicsPositive,
				top5ChemPositive, top5MathPositive, sansScore1, top5SansPositive, engScore1, top5EngPositive);
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(120, 390);
		contentStream.showText(FirstSubject);
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(220, 390);
		contentStream.showText(SecondSubject);
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(320, 390);
		contentStream.showText(ThirdSubject);
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(420, 390);
		contentStream.showText(FourthSubject);
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(520, 390);
		contentStream.showText(FifthSubject);
		contentStream.endText();
		addBarChart6Section5(contentStream, document, physicsScore2, chemistryScore2, mathsScore2, top5PhysicsNegative,
				top5ChemNegative, top5MathNegative, sansScore2, engScore2, top5SansNegative, top5EngNegative);
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(120, 150);
		contentStream.showText(FirstSubject);
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(220, 150);
		contentStream.showText(SecondSubject);
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(320, 150);
		contentStream.showText(ThirdSubject);
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(420, 150);
		contentStream.showText(FourthSubject);
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(520, 150);
		contentStream.showText(FifthSubject);
		contentStream.endText();

		float x411 = 150;
		float y411 = 360;
		float width411 = 325;
		float height411 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x411, y411, width411, height411);
		contentStream.stroke();

		float innerXa = x411 + 50;
		float innerYa = y411 + 3;
		float innerWidtha = 10;
		float innerHeighta = height411 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerXa, innerYa, innerWidtha, innerHeighta);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXa + 14, innerYa + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerXb = x411 + 105;
		float innerYb = y411 + 3;
		float innerWidthb = 10;
		float innerHeightb = height411 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerXb, innerYb, innerWidthb, innerHeightb);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXb + 14, innerYb + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerXc = x411 + 150;
		float innerYc = y411 + 3;
		float innerWidthc = 10;
		float innerHeightc = height411 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerXc, innerYc, innerWidthc, innerHeightc);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXc + 14, innerYc + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerXd = x411 + 200;
		float innerYd = y411 + 3;
		float innerWidthd = 10;
		float innerHeightd = height411 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerXd, innerYd, innerWidthd, innerHeightd);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXd + 14, innerYd + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		float x4111 = 150;
		float y4111 = 130;
		float width4111 = 325;
		float height4111 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x4111, y4111, width4111, height4111);
		contentStream.stroke();

		float innerXa1 = x4111 + 50;
		float innerYa1 = y4111 + 3;
		float innerWidtha1 = 10;
		float innerHeighta1 = height4111 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerXa1, innerYa1, innerWidtha1, innerHeighta1);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXa1 + 14, innerYa1 + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerXb1 = x4111 + 105;
		float innerYb1 = y4111 + 3;
		float innerWidthb1 = 10;
		float innerHeightb1 = height4111 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerXb1, innerYb1, innerWidthb1, innerHeightb1);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXb1 + 14, innerYb1 + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerXc1 = x4111 + 150;
		float innerYc1 = y4111 + 3;
		float innerWidthc1 = 10;
		float innerHeightc1 = height4111 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerXc1, innerYc1, innerWidthc1, innerHeightc1);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXc1 + 14, innerYc1 + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerXd1 = x4111 + 200;
		float innerYd1 = y4111 + 3;
		float innerWidthd1 = 10;
		float innerHeightd1 = height4111 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerXd1, innerYd1, innerWidthd1, innerHeightd1);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXd1 + 14, innerYd1 + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		contentStream.stroke();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart5Section5(PDPageContentStream contentStream, PDDocument document, int physicsScore1,
			int chemistryScore1, int mathsScore1, int sansScore1, int engScore1,String FirstSubject,String SecondSubject,String ThirdSubject,String FourthSubject,String FifthSubject) throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, (physicsScore1));
		dataset.setValue(SecondSubject, (chemistryScore1));
		dataset.setValue(ThirdSubject, (mathsScore1));
		dataset.setValue(FourthSubject, (sansScore1));
		dataset.setValue(FifthSubject, (engScore1));

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));
		plot.setSectionPaint(SecondSubject, new Color(255, 0, 0));
		plot.setSectionPaint(ThirdSubject, new Color(0, 255, 0));
		plot.setSectionPaint(FourthSubject, new Color(255, 165, 0));
		plot.setSectionPaint(FifthSubject, new Color(255, 255, 0));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 50, 600, 230, 140);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart6Section5(PDPageContentStream contentStream, PDDocument document, int physicsScore2,
			int chemistryScore2, int mathsScore2, int sansScore2, int engScore2,String FirstSubject,String SecondSubject,String ThirdSubject,String FourthSubject,String FifthSubject) throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, (physicsScore2));
		dataset.setValue(SecondSubject, (chemistryScore2));
		dataset.setValue(ThirdSubject, (mathsScore2));
		dataset.setValue(FourthSubject, (sansScore2));
		dataset.setValue(FifthSubject, (engScore2));

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));
		plot.setSectionPaint(SecondSubject, new Color(255, 0, 0));
		plot.setSectionPaint(ThirdSubject, new Color(0, 255, 0));
		plot.setSectionPaint(FourthSubject, new Color(255, 165, 0));
		plot.setSectionPaint(FifthSubject, new Color(255, 255, 0));


		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart1.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 350, 600, 230, 140);
	}

	private static void addBarChart5Section5(PDPageContentStream contentStream, PDDocument document, int physicsScore1,
			int chemistryScore1, int mathsScore1, List<StudentPhysicsPositiveScore> top5PhysicsPositive,
			List<StudentChemPositiveScore> top5ChemPositive, List<StudentMathPositiveScore> top5MathPositive,
			int sansScore1, List<StudentSansPositiveScore> top5SansPositive, int engScore1,
			List<StudentEngPositiveScore> top5EngPositive) throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue((physicsScore1), "phy positive Marks", "");
		int topCount = 1;
		for (StudentPhysicsPositiveScore studentScore : top5PhysicsPositive) {
			if (topCount > 3)
				break;
			dataset.addValue(studentScore.getScore(), "phy Top Marks " + topCount++, "");
		}
		dataset.addValue((chemistryScore1), "chem positive Marks", "");
		int topCount0 = 1;
		for (StudentChemPositiveScore studentScore : top5ChemPositive) {
			if (topCount0 > 3)
				break;
			dataset.addValue(studentScore.getScore(), "chem Top Marks " + topCount0++, "");
		}
		dataset.addValue((mathsScore1), "math positive Marks", "");
		int topCount1 = 1;
		for (StudentMathPositiveScore studentScore : top5MathPositive) {
			if (topCount1 > 3)
				break;
			dataset.addValue(studentScore.getScore(), "math Top Marks " + topCount1++, "");
		}
		dataset.addValue((sansScore1), "sans positive Marks", "");
		int topCount2 = 1;
		for (StudentSansPositiveScore studentScore : top5SansPositive) {
			if (topCount2 > 3)
				break;
			dataset.addValue(studentScore.getScore(), "sans Top Marks " + topCount2++, "");
		}
		dataset.addValue((engScore1), "eng positive Marks", "");
		int topCount3 = 1;
		for (StudentEngPositiveScore studentScore : top5EngPositive) {
			if (topCount3 > 3)
				break;
			dataset.addValue(studentScore.getScore(), "eng Top Marks " + topCount3++, "");
		}

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));

		BarRenderer renderer = (BarRenderer) plot.getRenderer();

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(0, 0, 255));
		renderer.setSeriesPaint(5, new Color(255, 0, 0));

		renderer.setSeriesPaint(6, new Color(0, 255, 0));
		renderer.setSeriesPaint(7, new Color(255, 165, 0));

		renderer.setSeriesPaint(8, new Color(0, 0, 255));
		renderer.setSeriesPaint(9, new Color(255, 0, 0));

		renderer.setSeriesPaint(10, new Color(0, 255, 0));
		renderer.setSeriesPaint(11, new Color(255, 165, 0));

		renderer.setSeriesPaint(12, new Color(0, 0, 255));
		renderer.setSeriesPaint(13, new Color(255, 0, 0));

		renderer.setSeriesPaint(14, new Color(0, 255, 0));
		renderer.setSeriesPaint(15, new Color(255, 165, 0));

		renderer.setSeriesPaint(16, new Color(0, 0, 255));
		renderer.setSeriesPaint(17, new Color(255, 0, 0));

		renderer.setSeriesPaint(18, new Color(0, 255, 0));
		renderer.setSeriesPaint(19, new Color(255, 165, 0));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 10));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(0, 12);
		yAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 7));
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		Font tickLabelFont = new Font("Arial", Font.PLAIN, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(1);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 10));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 15, 390, 580, 180);
	}

	private static void addBarChart6Section5(PDPageContentStream contentStream, PDDocument document, int physicsScore2,
			int chemistryScore2, int mathsScore2, List<StudentPhysicsNegativeScore> top5PhysicsNegative,
			List<StudentChemNegativeScore> top5ChemNegative, List<StudentMathNegativeScore> top5MathNegative,
			int sansScore2, int engScore2, List<StudentSansNegativeScore> top5SansNegative,
			List<StudentEngNegativeScore> top5EngNegative) throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue((physicsScore2), "phy negative Marks", "");
		int topCount = 1;
		for (StudentPhysicsNegativeScore studentScore : top5PhysicsNegative) {
			if (topCount > 3)
				break;
			dataset.addValue(studentScore.getScore(), "phy Top Marks " + topCount++, "");
		}
		dataset.addValue((chemistryScore2), "chem negative Marks", "");
		int topCount0 = 1;
		for (StudentChemNegativeScore studentScore : top5ChemNegative) {
			if (topCount0 > 3)
				break;
			dataset.addValue(studentScore.getScore(), "chem Top Marks " + topCount0++, "");
		}
		dataset.addValue((mathsScore2), "math negative Marks", "");
		int topCount1 = 1;
		for (StudentMathNegativeScore studentScore : top5MathNegative) {
			if (topCount1 > 3)
				break;
			dataset.addValue(studentScore.getScore(), "math Top Marks " + topCount1++, "");
		}

		dataset.addValue((sansScore2), "sans negative Marks", "");
		int topCount2 = 1;
		for (StudentSansNegativeScore studentScore : top5SansNegative) {
			if (topCount2 > 3)
				break;
			dataset.addValue(studentScore.getScore(), "sans Top Marks " + topCount2++, "");
		}

		dataset.addValue((engScore2), "eng negative Marks", "");
		int topCount3 = 1;
		for (StudentEngNegativeScore studentScore : top5EngNegative) {
			if (topCount3 > 3)
				break;
			dataset.addValue(studentScore.getScore(), "eng Top Marks " + topCount3++, "");
		}

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));

		BarRenderer renderer = (BarRenderer) plot.getRenderer();

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(0, 0, 255));
		renderer.setSeriesPaint(5, new Color(255, 0, 0));

		renderer.setSeriesPaint(6, new Color(0, 255, 0));
		renderer.setSeriesPaint(7, new Color(255, 165, 0));

		renderer.setSeriesPaint(8, new Color(0, 0, 255));
		renderer.setSeriesPaint(9, new Color(255, 0, 0));

		renderer.setSeriesPaint(10, new Color(0, 255, 0));
		renderer.setSeriesPaint(11, new Color(255, 165, 0));

		renderer.setSeriesPaint(12, new Color(0, 0, 255));
		renderer.setSeriesPaint(13, new Color(255, 0, 0));

		renderer.setSeriesPaint(14, new Color(0, 255, 0));
		renderer.setSeriesPaint(15, new Color(255, 165, 0));

		renderer.setSeriesPaint(16, new Color(0, 0, 255));
		renderer.setSeriesPaint(17, new Color(255, 0, 0));

		renderer.setSeriesPaint(18, new Color(0, 255, 0));
		renderer.setSeriesPaint(19, new Color(255, 165, 0));
		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 10));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(0, 12);
		yAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 7));
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		Font tickLabelFont = new Font("Arial", Font.PLAIN, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(1);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 10));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 15, 150, 580, 180);
	}

	@SuppressWarnings("unused")
	private static void addContentTopage5Section5(PDPageContentStream contentStream, PDDocument document,
			int physicsScore1, int chemistryScore1, int mathsScore1, int physicsScore2, int chemistryScore2,
			int mathsScore2, List<StudentPhysicsPositiveScore> top5PhysicsPositive,
			List<StudentChemPositiveScore> top5ChemPositive, List<StudentMathPositiveScore> top5MathPositive,
			List<StudentPhysicsNegativeScore> top5PhysicsNegative, List<StudentChemNegativeScore> top5ChemNegative,
			List<StudentMathNegativeScore> top5MathNegative, int sansScore1, int engScore1, int sansScore2,
			int engScore2, List<StudentSansPositiveScore> top5SansPositive,
			List<StudentEngPositiveScore> top5EngPositive, List<StudentSansNegativeScore> top5SansNegative,
			List<StudentEngNegativeScore> top5EngNegative,String FirstSubject,String SecondSubject,String ThirdSubject,String FourthSubject,String FifthSubject
) throws IOException {

		int initX = 50;
		int initY = 750;
		int cellHeight = 20;
		int cellWidth = 130;

		int colCount = 1;
		int rowCount = 7;

		String[][] tableData = { { "Subject" }, { "" }, { FirstSubject }, { SecondSubject }, { ThirdSubject }, { FourthSubject }, { FifthSubject }

		};

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				String content = tableData[i][j];

				PDType1Font font = (i == 0) ? new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
						: new PDType1Font(Standard14Fonts.FontName.HELVETICA);

				contentStream.addRect(initX, initY, cellWidth, -cellHeight);

				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.newLineAtOffset(initX + 40, initY - cellHeight + 5);
				contentStream.showText(content);
				contentStream.endText();

				initX += cellWidth;
			}
			initX = 50;
			initY -= cellHeight;
		}

		contentStream.stroke();
		int initX1 = 180;
		int initY1 = 750;
		int cellHeight1 = 20;
		int cellWidth1 = 30;

		int colCount1 = 6;
		int rowCount1 = 6;

		String[] headers = { "My Score", "Rank 1", "Rank 2", "Rank 3", "Rank 4", "Rank 5" };
		String[] subHeaders = { "+ve", "-ve", "+ve", "-ve", "+ve", "-ve", "+ve", "-ve", "+ve", "-ve", "+ve", "-ve" };

		int t1mp = top5MathPositive.get(0).getScore();
		int t2mp = top5MathPositive.get(1).getScore();
		int t3mp = top5MathPositive.get(2).getScore();
		int t4mp = top5MathPositive.get(3).getScore();
		int t5mp = top5MathPositive.get(4).getScore();

		int t1mn = top5MathNegative.get(0).getScore();
		int t2mn = top5MathNegative.get(1).getScore();
		int t3mn = top5MathNegative.get(2).getScore();
		int t4mn = top5MathNegative.get(3).getScore();
		int t5mn = top5MathNegative.get(4).getScore();

		int t1pp = top5PhysicsPositive.get(0).getScore();
		int t2pp = top5PhysicsPositive.get(1).getScore();
		int t3pp = top5PhysicsPositive.get(2).getScore();
		int t4pp = top5PhysicsPositive.get(3).getScore();
		int t5pp = top5PhysicsPositive.get(4).getScore();

		int t1pn = top5PhysicsNegative.get(0).getScore();
		int t2pn = top5PhysicsNegative.get(1).getScore();
		int t3pn = top5PhysicsNegative.get(2).getScore();
		int t4pn = top5PhysicsNegative.get(3).getScore();
		int t5pn = top5PhysicsNegative.get(4).getScore();

		int t1cp = top5ChemPositive.get(0).getScore();
		int t2cp = top5ChemPositive.get(1).getScore();
		int t3cp = top5ChemPositive.get(2).getScore();
		int t4cp = top5ChemPositive.get(3).getScore();
		int t5cp = top5ChemPositive.get(4).getScore();

		int t1cn = top5ChemNegative.get(0).getScore();
		int t2cn = top5ChemNegative.get(1).getScore();
		int t3cn = top5ChemNegative.get(2).getScore();
		int t4cn = top5ChemNegative.get(3).getScore();
		int t5cn = top5ChemNegative.get(4).getScore();

		int t1sp = top5SansPositive.get(0).getScore();
		int t2sp = top5SansPositive.get(1).getScore();
		int t3sp = top5SansPositive.get(2).getScore();
		int t4sp = top5SansPositive.get(3).getScore();
		int t5sp = top5SansPositive.get(4).getScore();

		int t1sn = top5SansNegative.get(0).getScore();
		int t2sn = top5SansNegative.get(1).getScore();
		int t3sn = top5SansNegative.get(2).getScore();
		int t4sn = top5SansNegative.get(3).getScore();
		int t5sn = top5SansNegative.get(4).getScore();

		int t1ep = top5EngPositive.get(0).getScore();
		int t2ep = top5EngPositive.get(1).getScore();
		int t3ep = top5EngPositive.get(2).getScore();
		int t4ep = top5EngPositive.get(3).getScore();
		int t5ep = top5EngPositive.get(4).getScore();

		int t1en = top5EngNegative.get(0).getScore();
		int t2en = top5EngNegative.get(1).getScore();
		int t3en = top5EngNegative.get(2).getScore();
		int t4en = top5EngNegative.get(3).getScore();
		int t5en = top5EngNegative.get(4).getScore();

		String[][] tableData1 = {
				
				{ Integer.toString(physicsScore1), Integer.toString(physicsScore2), Integer.toString(t1pp),
						Integer.toString(t1pn), Integer.toString(t2pp), Integer.toString(t2pn), Integer.toString(t3pp),
						Integer.toString(t3pn), Integer.toString(t4pp), Integer.toString(t4pn), Integer.toString(t5pp),
						Integer.toString(t5pn) },
				{ Integer.toString(chemistryScore1), Integer.toString(chemistryScore2), Integer.toString(t1cp),
						Integer.toString(t1cn), Integer.toString(t2cp), Integer.toString(t2cn), Integer.toString(t3cp),
						Integer.toString(t3cn), Integer.toString(t4cp), Integer.toString(t4cn), Integer.toString(t5cp),
						Integer.toString(t5cn) },
				{ Integer.toString(mathsScore1), Integer.toString(mathsScore2), Integer.toString(t1mp),
							Integer.toString(t1mn), Integer.toString(t2mp), Integer.toString(t2mn), Integer.toString(t3mp),
							Integer.toString(t3mn), Integer.toString(t4mp), Integer.toString(t4mn), Integer.toString(t5mp),
							Integer.toString(t5mn) },
				{ Integer.toString(sansScore1), Integer.toString(sansScore2), Integer.toString(t1sp),
						Integer.toString(t1sn), Integer.toString(t2sp), Integer.toString(t2sn), Integer.toString(t3sp),
						Integer.toString(t3sn), Integer.toString(t4sp), Integer.toString(t4sn), Integer.toString(t5sp),
						Integer.toString(t5sn) },
				{ Integer.toString(engScore1), Integer.toString(engScore2), Integer.toString(t1ep),
						Integer.toString(t1en), Integer.toString(t2ep), Integer.toString(t2en), Integer.toString(t3ep),
						Integer.toString(t3en), Integer.toString(t4ep), Integer.toString(t4en), Integer.toString(t5ep),
						Integer.toString(t5en) },

		};

		for (int i = 0; i < headers.length; i++) {
			String content = headers[i];
			contentStream.addRect(initX1, initY1, cellWidth1 * 2, -cellHeight1);
			contentStream.beginText();
			contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 9);
			contentStream.setNonStrokingColor(Color.BLACK);
			contentStream.newLineAtOffset(initX1 + 10, initY1 - cellHeight1 + 5);
			contentStream.showText(content);
			contentStream.endText();
			initX1 += cellWidth1 * 2;
		}
		initX1 = 180;
		initY1 -= cellHeight1;

		for (int i = 0; i < subHeaders.length; i++) {
			String content = subHeaders[i];
			contentStream.addRect(initX1, initY1, cellWidth1, -cellHeight1);
			contentStream.beginText();
			contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 9);
			contentStream.setNonStrokingColor(Color.BLACK);
			contentStream.newLineAtOffset(initX1 + 10, initY1 - cellHeight1 + 5);
			contentStream.showText(content);
			contentStream.endText();
			initX1 += cellWidth1;
		}
		initX1 = 180;
		initY1 -= cellHeight1;

		for (int i = 0; i < tableData1.length; i++) {
			for (int j = 0; j < tableData1[i].length; j++) {
				String content = tableData1[i][j];
				contentStream.addRect(initX1, initY1, cellWidth1, -cellHeight1);
				contentStream.beginText();
				contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.newLineAtOffset(initX1 + 10, initY1 - cellHeight1 + 5);
				contentStream.showText(content);
				contentStream.endText();
				initX1 += cellWidth1;
			}
			initX1 = 180;
			initY1 -= cellHeight1;
		}
		contentStream.stroke();

	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Section-4
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	@SuppressWarnings("unused")
	private static void addContentToPage1Section4(PDDocument document, PDPage page, PDPageContentStream contentStream,
			String imageName, int totalStudents, String scoredMarks, String maxMarks, String percentage,
			int physicsMarks, int totalPhysicsScore, String physicspercentage, int chemistryMarks,
			int totalchemistryScore, String chemistrypercentage, int mathsMarks, int totalmathsScore,
			String mathspercentage, String fileNameWithoutExtension, int sansMarks, int totalsansScore,
			String sanspercentage,String FirstSubject,String SecondSubject,String ThirdSubject,String FourthSubject) throws IOException {

		float[] textColor1 = hexToRGB("#F5C300");

		String scoreReportBelowText = fileNameWithoutExtension;
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 26);
		contentStream.setNonStrokingColor(textColor1[0], textColor1[1], textColor1[2]);
		float textWidth1 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(scoreReportBelowText)
				/ 1000f * 40;
		float textX1 = 70;
		float textY1 = 612;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX1, textY1));
		contentStream.showText(scoreReportBelowText);
		contentStream.endText();

		float[] textColor = hexToRGB("#6978aa");

		String scoreReportText = "Score Report";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 46);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(scoreReportText) / 1000f
				* 40;
		float textX = 70;
		float textY = 640;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX, textY));
		contentStream.showText(scoreReportText);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 46);
		contentStream.setNonStrokingColor(textColor[0], textColor[1], textColor[2]);
		float shadowOffsetX = 1;
		float shadowOffsetY = 1;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX + shadowOffsetX, textY - shadowOffsetY));
		contentStream.showText(scoreReportText);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 505);
		contentStream.showText(Integer.toString(totalStudents));
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 475);
		contentStream.showText(scoredMarks + "/" + maxMarks + "(" + percentage + ")");
		contentStream.endText();

		/*
		 * contentStream.beginText(); contentStream.setFont(new
		 * PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		 * contentStream.setNonStrokingColor(0f, 0f, 0f);
		 * contentStream.newLineAtOffset(425, 390); contentStream.showText(maxMarks);
		 * contentStream.endText();
		 */
		/*
		 * contentStream.beginText(); contentStream.setFont(new
		 * PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		 * contentStream.setNonStrokingColor(0f, 0f, 0f);
		 * contentStream.newLineAtOffset(443, 390);
		 * contentStream.showText("("+percentage+")"); contentStream.endText();
		 */
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(200, 445);
		contentStream.showText(FirstSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 445);
		contentStream
				.showText(physicsMarks + "/" + Integer.toString(totalPhysicsScore) + "(" + physicspercentage + ")");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(200, 415);
		contentStream.showText(SecondSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 415);
		contentStream.showText(
				chemistryMarks + "/" + Integer.toString(totalchemistryScore) + "(" + chemistrypercentage + ")");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(200, 385);
		contentStream.showText(ThirdSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 385);
		contentStream.showText(mathsMarks + "/" + Integer.toString(totalmathsScore) + "(" + mathspercentage + ")");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(200, 355);
		contentStream.showText(FourthSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		contentStream.newLineAtOffset(400, 355);
		contentStream.showText(sansMarks + "/" + Integer.toString(totalsansScore) + "(" + sanspercentage + ")");
		contentStream.endText();

		String candidateIDText = "Candidate ID";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth2 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(candidateIDText) / 1000f
				* 40;
		float textX2 = 200;
		float textY2 = 565;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX2, textY2));
		contentStream.showText(candidateIDText);
		contentStream.endText();

		String candidateIDansText = "5000041";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth3 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(candidateIDansText)
				/ 1000f * 40;
		float textX3 = 400;
		float textY3 = 565;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX3, textY3));
		contentStream.showText(candidateIDansText);
		contentStream.endText();

		String assessmentDateText = "Assessment Date";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth4 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(assessmentDateText)
				/ 1000f * 40;
		float textX4 = 200;
		float textY4 = 535;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX4, textY4));
		contentStream.showText(assessmentDateText);
		contentStream.endText();

		String assessmentdateansText = "July 16,2024";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth5 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(assessmentdateansText)
				/ 1000f * 40;
		float textX5 = 400;
		float textY5 = 535;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX5, textY5));
		contentStream.showText(assessmentdateansText);
		contentStream.endText();

		String TotalcanText = "Total Candidates";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth6 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(TotalcanText) / 1000f
				* 40;
		float textX6 = 200;
		float textY6 = 505;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX6, textY6));
		contentStream.showText(TotalcanText);
		contentStream.endText();

		String RawscoreText = "My Raw Score";
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
		contentStream.setNonStrokingColor(0f, 0f, 0f);
		float textWidth7 = new PDType1Font(Standard14Fonts.FontName.HELVETICA).getStringWidth(RawscoreText) / 1000f
				* 40;
		float textX7 = 200;
		float textY7 = 475;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textX7, textY7));
		contentStream.showText(RawscoreText);
		contentStream.endText();

		String imagePath = "C:\\Users\\kusha\\OneDrive\\Desktop\\workspace\\pdfbuilder\\Picture1.jpg";
		PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath, document);
		float imageWidth = pdImage.getWidth();
		float imageHeight = pdImage.getHeight();
		float pageWidth = page.getMediaBox().getWidth();
		float pageHeight = page.getMediaBox().getHeight();
		float padding = 20;
		float x = padding;
		float y = 30;
		float scaledWidth = pageWidth - 2 * padding;
		float scaledHeight = imageHeight * (scaledWidth / imageWidth);
		contentStream.drawImage(pdImage, x, y, scaledWidth, scaledHeight);

		String text = "Demo Institution | Institución de demostración";
		float fontSize = 17;
		float textWidthinst = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD).getStringWidth(text) / 1000
				* fontSize;
		float textXinst = 40;
		float textYinst = y + scaledHeight - 200;

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 17);
		contentStream.setNonStrokingColor(1f, 1f, 1f);
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textXinst, textYinst));
		contentStream.showText(text);
		contentStream.endText();

		String imagePath2 = "C:\\Users\\kusha\\OneDrive\\Desktop\\workspace\\pdfbuilder\\Picture2.png";
		PDImageXObject pdImage2 = PDImageXObject.createFromFile(imagePath2, document);
		float imageWidth2 = pdImage2.getWidth();
		float imageHeight2 = pdImage2.getHeight();
		float pageWidth2 = page.getMediaBox().getWidth();
		float pageHeight2 = page.getMediaBox().getHeight();
		float padding2 = 20;
		float x2 = padding2;
		float y2 = 694;
		float scaledWidth2 = pageWidth2 - 2 * padding2;
		float scaledHeight2 = imageHeight2 * (scaledWidth2 / imageWidth2);
		contentStream.drawImage(pdImage2, x2, y2, scaledWidth2, scaledHeight2);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 23);
		contentStream.setNonStrokingColor(1f, 1f, 1f);
		float textXStudentName = x2 + 420;
		float textYStudentName = y2 + 20;
		contentStream.setTextMatrix(Matrix.getTranslateInstance(textXStudentName, textYStudentName));
		contentStream.showText(imageName);
		contentStream.endText();

	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	private static void addContentToPage2Section4(PDPage page2, PDPageContentStream contentStream, String scoredMarks,
			PDDocument document, String maxMarks, double averageScoredMarks, List<StudentScore> top5Students,
			String correct, String incorrect, String unattempted, String phyScoredmarks, int averagePhyScoredMarks,
			List<StudentPhyScore> top5phyStudents, String chemScoredmarks, int PhyMaxMarks,
			List<StudentChemScore> top5chemStudents, int averageChemScoredMarks,
			List<StudentMathScore> top5mathStudents, String mathsScoredmarks, int averageMathScoredMarks,
			String sansScoredMarks, int averageSansScoredMarks, List<StudentSansScore> top5sansStudents,String FirstSubject,String SecondSubject,String ThirdSubject,String FourthSubject)
			throws IOException {

		contentStream.setNonStrokingColor(Color.YELLOW);

		float x = 20;
		float y = 750;
		float width = 570;
		float height = 20;

		contentStream.addRect(x, y, width, height);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x + 5, y + 5);
		contentStream.showText("Analysis of Overall Test");
		contentStream.endText();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));
		float x1 = 20;
		float y1 = 727;
		float width1 = 570;
		float height1 = 20;

		contentStream.addRect(x1, y1, width1, height1);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x1 + 5, y1 + 5);
		contentStream.showText("Comparison of Test Score");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 8);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(460, 715);
		contentStream.showText("My Correct, Incorrect &");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 8);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(448, 705);
		contentStream.showText("Unattempted Questions Count");
		contentStream.endText();

		addBarChart(contentStream, scoredMarks, document, maxMarks, averageScoredMarks, top5Students);
		addPieChart(contentStream, Integer.parseInt(correct), Integer.parseInt(incorrect),
				Integer.parseInt(unattempted), document);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(460, 550);
		contentStream.showText("Total Questions : " + maxMarks);
		contentStream.endText();

		int Attempt = Integer.parseInt(maxMarks) - Integer.parseInt(unattempted);
		float Attemptpercent = (Attempt / Integer.parseInt(maxMarks)) * 100;

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(469, 540);
		contentStream.showText("Attempt : " + Attemptpercent + " %");
		contentStream.endText();

		int Correct = Integer.parseInt(correct);
		int Max = Integer.parseInt(maxMarks);

		float temp = (Correct * 100) / Max;

		int Incorrect = Integer.parseInt(incorrect);
		float IncorrectPercent = (Incorrect * 100) / Max;

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(400, 515);
		contentStream.showText("Correct Ans : " + temp + "%" + " / " + "Incorrect Ans : " + IncorrectPercent + "%");
		contentStream.endText();

		float x2 = 40;
		float y2 = 510;
		float width2 = 355;
		float height2 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x2, y2, width2, height2);
		contentStream.stroke();

		float innerX = x2 + 5;
		float innerY = y2 + 3;
		float innerWidth = 10;
		float innerHeight = height2 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerX, innerY, innerWidth, innerHeight);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX + 14, innerY + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerX1 = x2 + 60;
		float innerY1 = y2 + 3;
		float innerWidth1 = 10;
		float innerHeight1 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerX1, innerY1, innerWidth1, innerHeight1);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX1 + 14, innerY1 + 1);
		contentStream.showText("Avg Score");
		contentStream.endText();

		float innerX2 = x2 + 120;
		float innerY2 = y2 + 3;
		float innerWidth2 = 10;
		float innerHeight2 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerX2, innerY2, innerWidth2, innerHeight2);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX2 + 14, innerY2 + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerX3 = x2 + 165;
		float innerY3 = y2 + 3;
		float innerWidth3 = 10;
		float innerHeight3 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerX3, innerY3, innerWidth3, innerHeight3);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX3 + 14, innerY3 + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerX4 = x2 + 210;
		float innerY4 = y2 + 3;
		float innerWidth4 = 10;
		float innerHeight4 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 255, 0));
		contentStream.addRect(innerX4, innerY4, innerWidth4, innerHeight4);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX4 + 14, innerY4 + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		float innerX5 = x2 + 260;
		float innerY5 = y2 + 3;
		float innerWidth5 = 10;
		float innerHeight5 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(160, 32, 240));
		contentStream.addRect(innerX5, innerY5, innerWidth5, innerHeight5);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX5 + 14, innerY5 + 1);
		contentStream.showText("Rank 4");
		contentStream.endText();

		float innerX6 = x2 + 310;
		float innerY6 = y2 + 3;
		float innerWidth6 = 10;
		float innerHeight6 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(173, 216, 230));
		contentStream.addRect(innerX6, innerY6, innerWidth6, innerHeight6);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX6 + 14, innerY6 + 1);
		contentStream.showText("Rank 5");
		contentStream.endText();

		float sectionX = 30;
		float sectionY = 495;

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(sectionX, sectionY);
		contentStream.showText("Test Toppers : ");

		StringBuilder topStudentsLine = new StringBuilder();
		for (int i = 0; i < Math.min(5, top5Students.size()); i++) {
			StudentScore student = top5Students.get(i);
			contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
			topStudentsLine.append(student.getName()).append(" (Score: ").append(student.getScore()).append(")");
			if (i < Math.min(5, top5Students.size()) - 1) {
				topStudentsLine.append(", ");
			}
		}
		contentStream.showText(topStudentsLine.toString());

		contentStream.endText();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));
		float x3 = 20;
		float y3 = 470;
		float width3 = 570;
		float height3 = 20;

		contentStream.addRect(x3, y3, width3, height3);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 5, y3 + 5);
		contentStream.showText("Comparison of Section wise Score");
		contentStream.endText();

		addBarChart2Section4(contentStream, phyScoredmarks, document, maxMarks, averagePhyScoredMarks, top5phyStudents,
				chemScoredmarks, PhyMaxMarks, top5chemStudents, averageChemScoredMarks, top5mathStudents,
				mathsScoredmarks, averageMathScoredMarks, sansScoredMarks, averageSansScoredMarks, top5sansStudents);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(150, 270);
		contentStream.showText(FirstSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(240, 270);
		contentStream.showText(SecondSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(380, 270);
		contentStream.showText(ThirdSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(470, 270);
		contentStream.showText(FourthSubject);
		contentStream.endText();

		float x4 = 120;
		float y4 = 250;
		float width4 = 355;
		float height4 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x4, y4, width4, height4);
		contentStream.stroke();

		float innerX7 = x4 + 65;
		float innerY7 = y4 + 3;
		float innerWidth7 = 10;
		float innerHeight7 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerX7, innerY7, innerWidth7, innerHeight7);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX7 + 14, innerY7 + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerX8 = x4 + 120;
		float innerY8 = y4 + 3;
		float innerWidth8 = 10;
		float innerHeight8 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerX8, innerY8, innerWidth8, innerHeight8);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX8 + 14, innerY8 + 1);
		contentStream.showText("Avg Score");
		contentStream.endText();

		float innerX9 = x4 + 180;
		float innerY9 = y4 + 3;
		float innerWidth9 = 10;
		float innerHeight9 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerX9, innerY9, innerWidth9, innerHeight9);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX9 + 14, innerY9 + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerX10 = x4 + 227;
		float innerY10 = y4 + 3;
		float innerWidth10 = 10;
		float innerHeight10 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerX10, innerY10, innerWidth10, innerHeight10);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX10 + 14, innerY10 + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerX11 = x4 + 271;
		float innerY11 = y4 + 3;
		float innerWidth11 = 10;
		float innerHeight11 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 255, 0));
		contentStream.addRect(innerX11, innerY11, innerWidth11, innerHeight11);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX11 + 14, innerY11 + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		int initX = 50;
		int initY = 230;
		int cellHeight = 20;
		int cellWidth = 65;

		int colCount = 8;
		int rowCount = 5;
		int m1 = top5mathStudents.get(0).getScore();
		int m2 = top5mathStudents.get(1).getScore();
		int m3 = top5mathStudents.get(2).getScore();
		int m4 = top5mathStudents.get(3).getScore();
		int m5 = top5mathStudents.get(4).getScore();

		int p1 = top5phyStudents.get(0).getScore();
		int p2 = top5phyStudents.get(1).getScore();
		int p3 = top5phyStudents.get(2).getScore();
		int p4 = top5phyStudents.get(3).getScore();
		int p5 = top5phyStudents.get(4).getScore();

		int c1 = top5chemStudents.get(0).getScore();
		int c2 = top5chemStudents.get(1).getScore();
		int c3 = top5chemStudents.get(2).getScore();
		int c4 = top5chemStudents.get(3).getScore();
		int c5 = top5chemStudents.get(4).getScore();

		int s1 = top5sansStudents.get(0).getScore();
		int s2 = top5sansStudents.get(1).getScore();
		int s3 = top5sansStudents.get(2).getScore();
		int s4 = top5sansStudents.get(3).getScore();
		int s5 = top5sansStudents.get(4).getScore();

		String[][] tableData = { { "Subject", "My Score", "Average", "Rank 1", "Rank 2", "Rank 3", "Rank 4", "Rank 5" },
				
				{ FirstSubject, phyScoredmarks, Float.toString((float) averagePhyScoredMarks), Integer.toString(p1),
						Integer.toString(p2), Integer.toString(p3), Integer.toString(p4), Integer.toString(p5) },
				{ SecondSubject, chemScoredmarks, Float.toString((float) averageChemScoredMarks), Integer.toString(c1),
						Integer.toString(c2), Integer.toString(c3), Integer.toString(c4), Integer.toString(c5) },
				{ ThirdSubject, mathsScoredmarks, Float.toString((float) averageMathScoredMarks), Integer.toString(m1),
							Integer.toString(m2), Integer.toString(m3), Integer.toString(m4), Integer.toString(m5) },
				{ FourthSubject, sansScoredMarks, Float.toString((float) averageSansScoredMarks), Integer.toString(s1),
						Integer.toString(s2), Integer.toString(s3), Integer.toString(s4), Integer.toString(s5) },

		};

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				String content = tableData[i][j];

				PDType1Font font = (i == 0) ? new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
						: new PDType1Font(Standard14Fonts.FontName.HELVETICA);

				contentStream.addRect(initX, initY, cellWidth, -cellHeight);

				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.newLineAtOffset(initX + 20, initY - cellHeight + 5);
				contentStream.showText(content);
				contentStream.endText();

				initX += cellWidth;
			}
			initX = 50;
			initY -= cellHeight;
		}

		contentStream.stroke();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));
		float xi = 20;
		float yi = 80;
		float widthi = 570;
		float heighti = 20;

		contentStream.addRect(xi, yi, widthi, heighti);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(xi + 5, yi + 5);
		contentStream.showText("Breakdown of Test Score");
		contentStream.endText();

	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Functions
	 * 
	 * 
	 * 
	 * 
	 */

	private static void addBarChart2Section4(PDPageContentStream contentStream, String phyScoredmarks,
			PDDocument document, String maxMarks, int averagePhyScoredMarks, List<StudentPhyScore> top5phyStudents,
			String chemScoredmarks, int PhyMaxMarks, List<StudentChemScore> top5chemStudents,
			int averageChemScoredMarks, List<StudentMathScore> top5mathStudents, String mathsScoredmarks,
			int averageMathScoredMarks, String sansScoredmarks, int averageSansScoredMarks,
			List<StudentSansScore> top5sansStudents) throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(Integer.parseInt(phyScoredmarks), "Scored Phy Marks", "");
		dataset.addValue((averagePhyScoredMarks), "Average Marks", "");

		int topCount = 1;
		for (StudentPhyScore studentScore : top5phyStudents) {
			if (topCount > 3)
				break;
			dataset.addValue(studentScore.getScore(), "Top Marks " + topCount++, "");
		}

		dataset.addValue(Integer.parseInt(chemScoredmarks), "Scored Chem Marks", "");
		dataset.addValue((averageChemScoredMarks), "Average Chem Marks", "");
		int topCount0 = 1;
		for (StudentChemScore studentScore : top5chemStudents) {
			if (topCount0 > 3)
				break;
			dataset.addValue(studentScore.getScore(), "Top Chem Marks " + topCount0++, "");
		}

		dataset.addValue(Integer.parseInt(mathsScoredmarks), "Scored math Marks", "");
		dataset.addValue((averageMathScoredMarks), "Average Math Marks", "");
		int topCount1 = 1;
		for (StudentMathScore studentScore : top5mathStudents) {
			if (topCount1 > 3)
				break;
			dataset.addValue(studentScore.getScore(), "Top Math Marks " + topCount1++, "");
		}

		dataset.addValue(Integer.parseInt(sansScoredmarks), "Scored sans Marks", "");
		dataset.addValue((averageSansScoredMarks), "Average Sans Marks", "");
		int topCount2 = 1;
		for (StudentSansScore studentScore : top5sansStudents) {
			if (topCount2 > 3)
				break;
			dataset.addValue(studentScore.getScore(), "Top Sans Marks " + topCount2++, "");
		}

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));
		plot.getDomainAxis().setCategoryMargin(2.0);

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setItemMargin(0.5);

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(255, 255, 0));

		renderer.setSeriesPaint(5, new Color(0, 0, 255));
		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(6, new Color(255, 0, 0));

		renderer.setSeriesPaint(7, new Color(0, 255, 0));
		renderer.setSeriesPaint(8, new Color(255, 165, 0));
		renderer.setSeriesPaint(9, new Color(255, 255, 0));

		renderer.setSeriesPaint(10, new Color(0, 0, 255));
		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(11, new Color(255, 0, 0));

		renderer.setSeriesPaint(12, new Color(0, 255, 0));
		renderer.setSeriesPaint(13, new Color(255, 165, 0));
		renderer.setSeriesPaint(14, new Color(255, 255, 0));

		renderer.setSeriesPaint(15, new Color(0, 0, 255));
		renderer.setSeriesPaint(16, new Color(255, 0, 0));

		renderer.setSeriesPaint(17, new Color(0, 255, 0));
		renderer.setSeriesPaint(18, new Color(255, 165, 0));
		renderer.setSeriesPaint(19, new Color(255, 255, 0));
		renderer.setSeriesPaint(20, new Color(0, 0, 255));
		renderer.setSeriesPaint(21, new Color(255, 0, 0));

		renderer.setSeriesPaint(22, new Color(0, 255, 0));
		renderer.setSeriesPaint(23, new Color(255, 165, 0));
		renderer.setSeriesPaint(24, new Color(255, 255, 0));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 8));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(0, PhyMaxMarks);

		yAxis.setLabelFont(new Font("Arial", Font.BOLD, 8));

		Font tickLabelFont = new Font("Arial", Font.BOLD, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(2.0);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 8));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 10, 265, 580, 200);
	}

	private static void addContentTopage3Section4(String imageName, PDPage page3, PDPageContentStream contentStream,
			PDDocument document, int totalPhysicsScore, int totalchemistryScore, int totalmathsScore, int physicsMarks,
			int chemistryMarks, int mathsMarks, int attemptmathQuestions, int mathsScore1, int mathsScore2,
			String mathspercentage, int attemptphysicsQuestions, int physicsScore1, int physicsScore2,
			String physicspercentage, int attemptchemistryQuestions, int chemistryScore1, int chemistryScore2,
			String chemistrypercentage, List<StudentMathScore> studentmathScores,
			List<StudentPhyScore> studentphyScores, List<StudentChemScore> studentchemScores, int physicsScore3,
			int chemScore3, int mathsScore3, List<StudentPositiveScore> top5Positive, String PositiveScore,
			List<StudentNegativeScore> top5Negative, String NegativeScore, int totalsansScore, int sansMarks,
			int attemptsansQuestions, int sansScore1, int sansScore2, int sansScore3, String sanspercentage,
			List<StudentSansScore> studentsansScores, String maxMarks,String FirstSubject,String SecondSubject,String ThirdSubject,String FourthSubject) throws IOException {

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(80, 750);
		contentStream.showText("Count of Questions");
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(270, 750);
		contentStream.showText("Marks of Questions");
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(480, 750);
		contentStream.showText("My Score");
		contentStream.endText();

		addPieChart2Section4(document, contentStream, totalPhysicsScore, totalchemistryScore, totalmathsScore,
				totalsansScore,FirstSubject,SecondSubject,ThirdSubject,FourthSubject);
		addPieChart3Section4(document, contentStream, totalPhysicsScore, totalchemistryScore, totalmathsScore,
				totalsansScore,FirstSubject,SecondSubject,ThirdSubject,FourthSubject);
		if (physicsMarks < 0 && chemistryMarks < 0 && mathsMarks < 0) {

			float x = 450;
			float y = 680;
			float width = 100;
			float height = 20;

			contentStream.addRect(x, y, width, height);
			contentStream.setNonStrokingColor(new Color(255, 127, 127));
			contentStream.fill();

			contentStream.setStrokingColor(Color.BLACK);
			contentStream.addRect(x, y, width, height);
			contentStream.stroke();

			contentStream.beginText();
			contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
			contentStream.setNonStrokingColor(Color.BLACK);
			contentStream.newLineAtOffset(x + 10, y + 5);
			contentStream.showText("Negative Score");
			contentStream.endText();
		} else {
			addPieChart4Section4(document, contentStream, physicsMarks, chemistryMarks, mathsMarks, sansMarks,FirstSubject,SecondSubject,ThirdSubject,FourthSubject);

		}
		int initX = 50;
		int initY = 600;
		int cellHeight = 20;
		int cellWidth = 60;

		int colCount = 9;
		int rowCount = 5;

		String targetImageName = imageName;
		int mathrank = getStudentRank(studentmathScores, targetImageName);
		int phyrank = getphyStudentRank(studentphyScores, targetImageName);
		int chemrank = getchemtudentRank(studentchemScores, targetImageName);
		int sansrank = getsanstudentRank(studentsansScores, targetImageName);

		String[][] tableData = {
				{ "Subject", "Total Q.", "Attempt Q.", "Correct", "Incorrect", "Raw Score", "Total(Max)", "Percent",
						"Rank" },
				
				{ FirstSubject, Integer.toString(totalPhysicsScore), Integer.toString(attemptphysicsQuestions),
						Integer.toString(physicsScore1), Integer.toString(physicsScore2),
						Integer.toString(physicsMarks), Integer.toString(totalPhysicsScore), physicspercentage,
						Integer.toString(phyrank) },
				{ SecondSubject, Integer.toString(totalchemistryScore), Integer.toString(attemptchemistryQuestions),
						Integer.toString(chemistryScore1), Integer.toString(chemistryScore2),
						Integer.toString(chemistryMarks), Integer.toString(totalchemistryScore), chemistrypercentage,
						Integer.toString(chemrank) },
				{ ThirdSubject, Integer.toString(totalmathsScore), Integer.toString(attemptmathQuestions),
							Integer.toString(mathsScore1), Integer.toString(mathsScore2), Integer.toString(mathsMarks),
							Integer.toString(totalmathsScore), mathspercentage, Integer.toString(mathrank) },
				{ FourthSubject, Integer.toString(totalsansScore), Integer.toString(attemptsansQuestions),
						Integer.toString(sansScore1), Integer.toString(sansScore2), Integer.toString(sansMarks),
						Integer.toString(totalsansScore), sanspercentage, Integer.toString(sansrank) },

		};

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				String content = tableData[i][j];

				PDType1Font font = (i == 0) ? new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
						: new PDType1Font(Standard14Fonts.FontName.HELVETICA);

				contentStream.addRect(initX, initY, cellWidth, -cellHeight);

				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.newLineAtOffset(initX + 10, initY - cellHeight + 5);
				contentStream.showText(content);
				contentStream.endText();

				initX += cellWidth;
			}
			initX = 50;
			initY -= cellHeight;
		}

		contentStream.stroke();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));

		float x = 20;
		float y = 440;
		float width = 570;
		float height = 20;

		contentStream.addRect(x, y, width, height);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x + 5, y + 5);
		contentStream.showText("Analysis of Overall Test");
		contentStream.endText();

		addBarChart31Section4(contentStream, totalPhysicsScore, totalchemistryScore, totalmathsScore, document,
				physicsScore1, physicsScore2, physicsScore3, chemistryScore1, chemistryScore2, chemScore3, mathsScore1,
				mathsScore2, mathsScore3, totalsansScore, sansScore1, sansScore2, sansScore3);

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(110, 270);
		contentStream.showText(FirstSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(240, 270);
		contentStream.showText(SecondSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(350, 270);
		contentStream.showText(ThirdSubject);
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(480, 270);
		contentStream.showText(FourthSubject);
		contentStream.endText();

		float x2 = 140;
		float y2 = 240;
		float width2 = 355;
		float height2 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x2, y2, width2, height2);
		contentStream.stroke();

		float innerX = x2 + 30;
		float innerY = y2 + 3;
		float innerWidth = 10;
		float innerHeight = height2 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerX, innerY, innerWidth, innerHeight);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX + 14, innerY + 1);
		contentStream.showText("Total Questions");
		contentStream.endText();

		float innerX1 = x2 + 110;
		float innerY1 = y2 + 3;
		float innerWidth1 = 10;
		float innerHeight1 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerX1, innerY1, innerWidth1, innerHeight1);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX1 + 14, innerY1 + 1);
		contentStream.showText("Correct Questions");
		contentStream.endText();

		float innerX2 = x2 + 200;
		float innerY2 = y2 + 3;
		float innerWidth2 = 10;
		float innerHeight2 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerX2, innerY2, innerWidth2, innerHeight2);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX2 + 14, innerY2 + 1);
		contentStream.showText("Incorrect Questions");
		contentStream.endText();

		float innerX3 = x2 + 300;
		float innerY3 = y2 + 3;
		float innerWidth3 = 10;
		float innerHeight3 = height2 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerX3, innerY3, innerWidth3, innerHeight3);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX3 + 14, innerY3 + 1);
		contentStream.showText("Skipped");
		contentStream.endText();
		contentStream.setNonStrokingColor(new Color(255, 255, 0));

		float x3 = 20;
		float y3 = 200;
		float width3 = 570;
		float height3 = 20;

		contentStream.addRect(x3, y3, width3, height3);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 5, y3 + 5);
		contentStream.showText("Comparison of Positive Score");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 300, y3 + 5);
		contentStream.showText("Comparison of Negative Score");
		contentStream.endText();

		addBarChart3Section5(contentStream, totalPhysicsScore, document, top5Positive, PositiveScore, maxMarks);
		addBarChart4Section5(contentStream, totalPhysicsScore, document, top5Negative, NegativeScore, maxMarks);

		float x4 = 100;
		float y4 = 40;
		float width4 = 355;
		float height4 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x4, y4, width4, height4);
		contentStream.stroke();

		float innerXa = x4 + 30;
		float innerYa = y4 + 3;
		float innerWidtha = 10;
		float innerHeighta = height4 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerXa, innerYa, innerWidtha, innerHeighta);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXa + 14, innerYa + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerXb = x4 + 85;
		float innerYb = y4 + 3;
		float innerWidthb = 10;
		float innerHeightb = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerXb, innerYb, innerWidthb, innerHeightb);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXb + 14, innerYb + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerXc = x4 + 130;
		float innerYc = y4 + 3;
		float innerWidthc = 10;
		float innerHeightc = height4 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerXc, innerYc, innerWidthc, innerHeightc);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXc + 14, innerYc + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerXd = x4 + 175;
		float innerYd = y4 + 3;
		float innerWidthd = 10;
		float innerHeightd = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerXd, innerYd, innerWidthd, innerHeightd);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXd + 14, innerYd + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		float innerX4 = x4 + 220;
		float innerY4 = y4 + 3;
		float innerWidth4 = 10;
		float innerHeight4 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(255, 255, 0));
		contentStream.addRect(innerX4, innerY4, innerWidth4, innerHeight4);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX4 + 14, innerY4 + 1);
		contentStream.showText("Rank 4");
		contentStream.endText();

		float innerX5 = x4 + 270;
		float innerY5 = y4 + 3;
		float innerWidth5 = 10;
		float innerHeight5 = height4 - 6;

		contentStream.setNonStrokingColor(new Color(162, 32, 240));
		contentStream.addRect(innerX5, innerY5, innerWidth5, innerHeight5);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerX5 + 14, innerY5 + 1);
		contentStream.showText("Rank 5");
		contentStream.endText();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart2Section4(PDDocument document, PDPageContentStream contentStream,
			int totalPhysicsScore, int totalchemistryScore, int totalmathsScore, int totalsansScore,String FirstSubject,String SecondSubject,String ThirdSubject,String FourthSubject)
			throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, totalPhysicsScore);
		dataset.setValue(SecondSubject, totalchemistryScore);
		dataset.setValue(ThirdSubject, totalmathsScore);
		dataset.setValue(FourthSubject, totalsansScore);

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));
		plot.setSectionPaint(SecondSubject, new Color(255, 0, 0));
		plot.setSectionPaint(ThirdSubject, new Color(0, 255, 0));
		plot.setSectionPaint(FourthSubject, new Color(255, 165, 0));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 30, 605, 210, 130);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart3Section4(PDDocument document, PDPageContentStream contentStream,
			int totalPhysicsScore, int totalchemistryScore, int totalmathsScore, int totalsansScore
			,String FirstSubject,String SecondSubject,String ThirdSubject,String FourthSubject)
			throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, totalPhysicsScore);
		dataset.setValue(SecondSubject, totalchemistryScore);
		dataset.setValue(ThirdSubject, totalmathsScore);
		dataset.setValue(FourthSubject, totalsansScore);

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));
		plot.setSectionPaint(SecondSubject, new Color(255, 0, 0));
		plot.setSectionPaint(ThirdSubject, new Color(0, 255, 0));
		plot.setSectionPaint(FourthSubject, new Color(255, 165, 0));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 225, 605, 210, 130);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart4Section4(PDDocument document, PDPageContentStream contentStream, int physicsMarks,
			int chemistryMarks, int mathsMarks, int sansMarks,String FirstSubject,String SecondSubject,String ThirdSubject,String FourthSubject) throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, (physicsMarks));
		dataset.setValue(SecondSubject, (chemistryMarks));
		dataset.setValue(ThirdSubject, (mathsMarks));
		dataset.setValue(FourthSubject, (sansMarks));

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));
		plot.setSectionPaint(SecondSubject, new Color(255, 0, 0));
		plot.setSectionPaint(ThirdSubject, new Color(0, 255, 0));
		plot.setSectionPaint(FourthSubject, new Color(255, 165, 0));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart1.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 415, 605, 210, 130);
	}

	private static void addBarChart31Section4(PDPageContentStream contentStream, int totalPhysicsScore,
			int totalChemistryScore, int totalmathsScore, PDDocument document, int physicsScore1, int physicsScore2,
			int physicsScore3, int chemScore1, int chemScore2, int chemScore3, int mathsScore1, int mathsScore2,
			int mathsScore3, int totalsansScore, int sansScore1, int sansScore2, int sansScore3) throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue((totalPhysicsScore), "Scored Phy Marks", "");
		dataset.addValue((physicsScore1), "phy score 1", "");
		dataset.addValue((physicsScore2), "phy score 2", "");
		dataset.addValue((physicsScore3), "phy score 3", "");

		dataset.addValue((totalChemistryScore), "Scored chem Marks", "");
		dataset.addValue((chemScore1), "chem  score 1", "");
		dataset.addValue((chemScore2), "chem score 2", "");
		dataset.addValue((chemScore3), "chem  score 3", "");

		dataset.addValue((totalmathsScore), "Scored math Marks", "");
		dataset.addValue((mathsScore1), "math score 1", "");
		dataset.addValue((mathsScore2), "math score 2", "");
		dataset.addValue((mathsScore3), "math score 3", "");

		dataset.addValue((totalsansScore), "Scored sans Marks", "");
		dataset.addValue((sansScore1), "sans score 1", "");
		dataset.addValue((sansScore2), "sans score 2", "");
		dataset.addValue((sansScore3), "sans score 3", "");

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));
		plot.getDomainAxis().setCategoryMargin(2.0);

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setItemMargin(0.5);

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(0, 0, 255));
		renderer.setSeriesPaint(5, new Color(255, 0, 0));
		renderer.setSeriesPaint(6, new Color(0, 255, 0));
		renderer.setSeriesPaint(7, new Color(255, 165, 0));

		renderer.setSeriesPaint(8, new Color(0, 0, 255));
		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(9, new Color(255, 0, 0));

		renderer.setSeriesPaint(10, new Color(0, 255, 0));
		renderer.setSeriesPaint(11, new Color(255, 165, 0));
		renderer.setSeriesPaint(12, new Color(0, 0, 255));
		renderer.setSeriesPaint(13, new Color(255, 0, 0));
		renderer.setSeriesPaint(14, new Color(0, 255, 0));
		renderer.setSeriesPaint(15, new Color(255, 165, 0));
		renderer.setSeriesPaint(16, new Color(0, 0, 255));
		renderer.setSeriesPaint(17, new Color(255, 0, 0));
		renderer.setSeriesPaint(18, new Color(0, 255, 0));
		renderer.setSeriesPaint(19, new Color(255, 165, 0));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 8));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(0.0, 10);

		yAxis.setLabelFont(new Font("Arial", Font.BOLD, 8));

		Font tickLabelFont = new Font("Arial", Font.BOLD, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(2.0);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 8));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 10, 280, 580, 150);
	}

	private static void addContentTopage4Section4(PDPageContentStream contentStream, PDDocument document,
			int physicsScore1, int chemistryScore1, int mathsScore1, int physicsScore2, int chemistryScore2,
			int mathsScore2, List<StudentPhysicsPositiveScore> top5PhysicsPositive,
			List<StudentChemPositiveScore> top5ChemPositive, List<StudentMathPositiveScore> top5MathPositive,
			List<StudentPhysicsNegativeScore> top5PhysicsNegative, List<StudentChemNegativeScore> top5ChemNegative,
			List<StudentMathNegativeScore> top5MathNegative, int sansScore1, int sansScore2,
			List<StudentSansPositiveScore> top5SansPositive, List<StudentSansNegativeScore> top5SansNegative
			,String FirstSubject,String SecondSubject,String ThirdSubject,String FourthSubject)
			throws IOException {
		contentStream.setNonStrokingColor(new Color(0, 149, 193));

		float x3 = 20;
		float y3 = 750;
		float width3 = 570;
		float height3 = 20;

		contentStream.addRect(x3, y3, width3, height3);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 5, y3 + 5);
		contentStream.showText("Distribution of Section wise Positive Score");
		contentStream.endText();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x3 + 300, y3 + 5);
		contentStream.showText("Distribution of Section wise Negative Score");
		contentStream.endText();

		addPieChart5Section4(contentStream, document, physicsScore1, chemistryScore1, mathsScore1, sansScore1,FirstSubject,SecondSubject,ThirdSubject,FourthSubject);
		addPieChart6Section4(contentStream, document, physicsScore2, chemistryScore2, mathsScore2, sansScore2,FirstSubject,SecondSubject,ThirdSubject,FourthSubject);

		contentStream.setNonStrokingColor(new Color(0, 149, 193));

		float x4 = 20;
		float y4 = 580;
		float width4 = 570;
		float height4 = 20;

		contentStream.addRect(x4, y4, width4, height4);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x4 + 5, y4 + 5);
		contentStream.showText("Comparision of Section wise Positive Score");
		contentStream.endText();

		contentStream.setNonStrokingColor(new Color(0, 149, 193));

		float x41 = 20;
		float y41 = 330;
		float width41 = 570;
		float height41 = 20;

		contentStream.addRect(x41, y41, width41, height41);
		contentStream.fill();

		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 11);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(x41 + 5, y41 + 5);
		contentStream.showText("Comparision of Section wise Negative Score");
		contentStream.endText();

		addBarChart5Section4(contentStream, document, physicsScore1, chemistryScore1, mathsScore1, top5PhysicsPositive,
				top5ChemPositive, top5MathPositive, sansScore1, top5SansPositive);
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(150, 390);
		contentStream.showText(FirstSubject);
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(270, 390);
		contentStream.showText(SecondSubject);
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(380, 390);
		contentStream.showText(ThirdSubject);
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(490, 390);
		contentStream.showText(FourthSubject);
		contentStream.endText();

		addBarChart6Section4(contentStream, document, physicsScore2, chemistryScore2, mathsScore2, top5PhysicsNegative,
				top5ChemNegative, top5MathNegative, sansScore2, top5SansNegative);
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(150, 150);
		contentStream.showText(FirstSubject);
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(270, 150);
		contentStream.showText(SecondSubject);
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(380, 150);
		contentStream.showText(ThirdSubject);
		contentStream.endText();
		contentStream.beginText();
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 7);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(490, 150);
		contentStream.showText(FourthSubject);
		contentStream.endText();

		float x411 = 150;
		float y411 = 360;
		float width411 = 325;
		float height411 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x411, y411, width411, height411);
		contentStream.stroke();

		float innerXa = x411 + 50;
		float innerYa = y411 + 3;
		float innerWidtha = 10;
		float innerHeighta = height411 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerXa, innerYa, innerWidtha, innerHeighta);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXa + 14, innerYa + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerXb = x411 + 105;
		float innerYb = y411 + 3;
		float innerWidthb = 10;
		float innerHeightb = height411 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerXb, innerYb, innerWidthb, innerHeightb);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXb + 14, innerYb + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerXc = x411 + 150;
		float innerYc = y411 + 3;
		float innerWidthc = 10;
		float innerHeightc = height411 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerXc, innerYc, innerWidthc, innerHeightc);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXc + 14, innerYc + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerXd = x411 + 200;
		float innerYd = y411 + 3;
		float innerWidthd = 10;
		float innerHeightd = height411 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerXd, innerYd, innerWidthd, innerHeightd);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXd + 14, innerYd + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		float x4111 = 150;
		float y4111 = 130;
		float width4111 = 325;
		float height4111 = 15;

		contentStream.setStrokingColor(Color.BLACK);
		contentStream.setLineWidth(1);
		contentStream.addRect(x4111, y4111, width4111, height4111);
		contentStream.stroke();

		float innerXa1 = x4111 + 50;
		float innerYa1 = y4111 + 3;
		float innerWidtha1 = 10;
		float innerHeighta1 = height4111 - 6;

		contentStream.setNonStrokingColor(new Color(0, 0, 255));
		contentStream.addRect(innerXa1, innerYa1, innerWidtha1, innerHeighta1);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXa1 + 14, innerYa1 + 1);
		contentStream.showText("My Score");
		contentStream.endText();

		float innerXb1 = x4111 + 105;
		float innerYb1 = y4111 + 3;
		float innerWidthb1 = 10;
		float innerHeightb1 = height4111 - 6;

		contentStream.setNonStrokingColor(new Color(255, 0, 0));
		contentStream.addRect(innerXb1, innerYb1, innerWidthb1, innerHeightb1);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXb1 + 14, innerYb1 + 1);
		contentStream.showText("Rank 1");
		contentStream.endText();

		float innerXc1 = x4111 + 150;
		float innerYc1 = y4111 + 3;
		float innerWidthc1 = 10;
		float innerHeightc1 = height4111 - 6;

		contentStream.setNonStrokingColor(new Color(0, 255, 0));
		contentStream.addRect(innerXc1, innerYc1, innerWidthc1, innerHeightc1);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXc1 + 14, innerYc1 + 1);
		contentStream.showText("Rank 2");
		contentStream.endText();

		float innerXd1 = x4111 + 200;
		float innerYd1 = y4111 + 3;
		float innerWidthd1 = 10;
		float innerHeightd1 = height4111 - 6;

		contentStream.setNonStrokingColor(new Color(255, 165, 0));
		contentStream.addRect(innerXd1, innerYd1, innerWidthd1, innerHeightd1);
		contentStream.fill();

		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.beginText();
		contentStream.newLineAtOffset(innerXd1 + 14, innerYd1 + 1);
		contentStream.showText("Rank 3");
		contentStream.endText();

		contentStream.stroke();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart5Section4(PDPageContentStream contentStream, PDDocument document, int physicsScore1,
			int chemistryScore1, int mathsScore1, int sansScore1,String FirstSubject,String SecondSubject,String ThirdSubject,String FourthSubject) throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, (physicsScore1));
		dataset.setValue(SecondSubject, (chemistryScore1));
		dataset.setValue(ThirdSubject, (mathsScore1));
		dataset.setValue(FourthSubject, (sansScore1));

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));
		plot.setSectionPaint(SecondSubject, new Color(255, 0, 0));
		plot.setSectionPaint(ThirdSubject, new Color(0, 255, 0));
		plot.setSectionPaint(FourthSubject, new Color(255, 165, 0));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 50, 600, 230, 140);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void addPieChart6Section4(PDPageContentStream contentStream, PDDocument document, int physicsScore2,
			int chemistryScore2, int mathsScore2, int sansScore2,String FirstSubject,String SecondSubject,String ThirdSubject,String FourthSubject) throws IOException {

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(FirstSubject, (physicsScore2));
		dataset.setValue(SecondSubject, (chemistryScore2));
		dataset.setValue(ThirdSubject, (mathsScore2));
		dataset.setValue(FourthSubject, (sansScore2));

		JFreeChart pieChart = ChartFactory.createPieChart(null, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineStroke(new BasicStroke(1));
		plot.setShadowPaint(null);
		plot.setOutlineVisible(false);

		LegendTitle legend = pieChart.getLegend();
		legend.setFrame(BlockBorder.NONE);
		legend.setVisible(false);

		plot.setSectionPaint(FirstSubject, new Color(0, 0, 255));
		plot.setSectionPaint(SecondSubject, new Color(255, 0, 0));
		plot.setSectionPaint(ThirdSubject, new Color(0, 255, 0));
		plot.setSectionPaint(FourthSubject, new Color(255, 165, 0));

		plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
		plot.setDefaultSectionOutlineStroke(new BasicStroke(1));
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		plot.setLabelLinkPaint(Color.BLACK);
		plot.setLabelLinkStroke(new BasicStroke(1.0f));

		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

		File chartFile = new File("pie_chart1.png");
		ChartUtils.saveChartAsPNG(chartFile, pieChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 350, 600, 230, 140);
	}

	private static void addBarChart5Section4(PDPageContentStream contentStream, PDDocument document, int physicsScore1,
			int chemistryScore1, int mathsScore1, List<StudentPhysicsPositiveScore> top5PhysicsPositive,
			List<StudentChemPositiveScore> top5ChemPositive, List<StudentMathPositiveScore> top5MathPositive,
			int sansScore1, List<StudentSansPositiveScore> top5SansPositive) throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue((physicsScore1), "phy positive Marks", "");
		int topCount = 1;
		for (StudentPhysicsPositiveScore studentScore : top5PhysicsPositive) {
			if (topCount > 3)
				break;
			dataset.addValue(studentScore.getScore(), "phy Top Marks " + topCount++, "");
		}
		dataset.addValue((chemistryScore1), "chem positive Marks", "");
		int topCount0 = 1;
		for (StudentChemPositiveScore studentScore : top5ChemPositive) {
			if (topCount0 > 3)
				break;
			dataset.addValue(studentScore.getScore(), "chem Top Marks " + topCount0++, "");
		}
		dataset.addValue((mathsScore1), "math positive Marks", "");
		int topCount1 = 1;
		for (StudentMathPositiveScore studentScore : top5MathPositive) {
			if (topCount1 > 3)
				break;
			dataset.addValue(studentScore.getScore(), "math Top Marks " + topCount1++, "");
		}
		dataset.addValue((sansScore1), "sans positive Marks", "");
		int topCount2 = 1;
		for (StudentSansPositiveScore studentScore : top5SansPositive) {
			if (topCount2 > 3)
				break;
			dataset.addValue(studentScore.getScore(), "sans Top Marks " + topCount2++, "");
		}

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));

		BarRenderer renderer = (BarRenderer) plot.getRenderer();

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(0, 0, 255));
		renderer.setSeriesPaint(5, new Color(255, 0, 0));

		renderer.setSeriesPaint(6, new Color(0, 255, 0));
		renderer.setSeriesPaint(7, new Color(255, 165, 0));

		renderer.setSeriesPaint(8, new Color(0, 0, 255));
		renderer.setSeriesPaint(9, new Color(255, 0, 0));

		renderer.setSeriesPaint(10, new Color(0, 255, 0));
		renderer.setSeriesPaint(11, new Color(255, 165, 0));

		renderer.setSeriesPaint(12, new Color(0, 0, 255));
		renderer.setSeriesPaint(13, new Color(255, 0, 0));

		renderer.setSeriesPaint(14, new Color(0, 255, 0));
		renderer.setSeriesPaint(15, new Color(255, 165, 0));

		renderer.setSeriesPaint(16, new Color(0, 0, 255));
		renderer.setSeriesPaint(17, new Color(255, 0, 0));

		renderer.setSeriesPaint(18, new Color(0, 255, 0));
		renderer.setSeriesPaint(19, new Color(255, 165, 0));

		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 10));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(0, 12);
		yAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 7));
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		Font tickLabelFont = new Font("Arial", Font.PLAIN, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(1);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 10));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 15, 390, 580, 180);
	}

	private static void addBarChart6Section4(PDPageContentStream contentStream, PDDocument document, int physicsScore2,
			int chemistryScore2, int mathsScore2, List<StudentPhysicsNegativeScore> top5PhysicsNegative,
			List<StudentChemNegativeScore> top5ChemNegative, List<StudentMathNegativeScore> top5MathNegative,
			int sansScore2, List<StudentSansNegativeScore> top5SansNegative) throws IOException {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue((physicsScore2), "phy negative Marks", "");
		int topCount = 1;
		for (StudentPhysicsNegativeScore studentScore : top5PhysicsNegative) {
			if (topCount > 3)
				break;
			dataset.addValue(studentScore.getScore(), "phy Top Marks " + topCount++, "");
		}
		dataset.addValue((chemistryScore2), "chem negative Marks", "");
		int topCount0 = 1;
		for (StudentChemNegativeScore studentScore : top5ChemNegative) {
			if (topCount0 > 3)
				break;
			dataset.addValue(studentScore.getScore(), "chem Top Marks " + topCount0++, "");
		}
		dataset.addValue((mathsScore2), "math negative Marks", "");
		int topCount1 = 1;
		for (StudentMathNegativeScore studentScore : top5MathNegative) {
			if (topCount1 > 3)
				break;
			dataset.addValue(studentScore.getScore(), "math Top Marks " + topCount1++, "");
		}

		dataset.addValue((sansScore2), "sans negative Marks", "");
		int topCount2 = 1;
		for (StudentSansNegativeScore studentScore : top5SansNegative) {
			if (topCount2 > 3)
				break;
			dataset.addValue(studentScore.getScore(), "sans Top Marks " + topCount2++, "");
		}

		JFreeChart barChart = ChartFactory.createBarChart(null, null, null, dataset, PlotOrientation.VERTICAL, false,
				false, false);

		CategoryPlot plot = (CategoryPlot) barChart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setRangeGridlineStroke(new BasicStroke(1));

		BarRenderer renderer = (BarRenderer) plot.getRenderer();

		renderer.setSeriesOutlineStroke(0, new BasicStroke(3.0f));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(255, 0, 0));

		renderer.setSeriesPaint(2, new Color(0, 255, 0));
		renderer.setSeriesPaint(3, new Color(255, 165, 0));
		renderer.setSeriesPaint(4, new Color(0, 0, 255));
		renderer.setSeriesPaint(5, new Color(255, 0, 0));

		renderer.setSeriesPaint(6, new Color(0, 255, 0));
		renderer.setSeriesPaint(7, new Color(255, 165, 0));

		renderer.setSeriesPaint(8, new Color(0, 0, 255));
		renderer.setSeriesPaint(9, new Color(255, 0, 0));

		renderer.setSeriesPaint(10, new Color(0, 255, 0));
		renderer.setSeriesPaint(11, new Color(255, 165, 0));

		renderer.setSeriesPaint(12, new Color(0, 0, 255));
		renderer.setSeriesPaint(13, new Color(255, 0, 0));

		renderer.setSeriesPaint(14, new Color(0, 255, 0));
		renderer.setSeriesPaint(15, new Color(255, 165, 0));

		renderer.setSeriesPaint(16, new Color(0, 0, 255));
		renderer.setSeriesPaint(17, new Color(255, 0, 0));

		renderer.setSeriesPaint(18, new Color(0, 255, 0));
		renderer.setSeriesPaint(19, new Color(255, 165, 0));
		plot.setOutlineVisible(true);
		plot.setOutlineStroke(new BasicStroke(1));

		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 10));
		xAxis.setAxisLineVisible(false);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(0, 12);
		yAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 7));
		yAxis.setAxisLineVisible(false);
		yAxis.setTickMarksVisible(false);
		Font tickLabelFont = new Font("Arial", Font.PLAIN, 7);
		yAxis.setTickLabelFont(tickLabelFont);
		yAxis.setAxisLineStroke(new BasicStroke(1));

		renderer.setMaximumBarWidth(1);

		plot.getDomainAxis().setCategoryMargin(0.2);

		renderer.setBarPainter(new StandardBarPainter());

		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Arial", Font.PLAIN, 10));
		renderer.setDefaultStroke(new BasicStroke(1));
		renderer.setDefaultPositiveItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
		renderer.setDefaultNegativeItemLabelPosition(
				new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));

		File chartFile = new File("bar_chart.png");
		ChartUtils.saveChartAsPNG(chartFile, barChart, CHART_WIDTH, CHART_HEIGHT);

		contentStream.drawImage(PDImageXObject.createFromFileByContent(chartFile, document), 15, 150, 580, 180);
	}

	@SuppressWarnings("unused")
	private static void addContentTopage5Section4(PDPageContentStream contentStream, PDDocument document,
			int physicsScore1, int chemistryScore1, int mathsScore1, int physicsScore2, int chemistryScore2,
			int mathsScore2, List<StudentPhysicsPositiveScore> top5PhysicsPositive,
			List<StudentChemPositiveScore> top5ChemPositive, List<StudentMathPositiveScore> top5MathPositive,
			List<StudentPhysicsNegativeScore> top5PhysicsNegative, List<StudentChemNegativeScore> top5ChemNegative,
			List<StudentMathNegativeScore> top5MathNegative, int sansScore1, int sansScore2,
			List<StudentSansPositiveScore> top5SansPositive, List<StudentSansNegativeScore> top5SansNegative,String FirstSubject,String SecondSubject,String ThirdSubject,String FourthSubject)
			throws IOException {

		int initX = 50;
		int initY = 750;
		int cellHeight = 20;
		int cellWidth = 130;

		int colCount = 1;
		int rowCount = 6;

		String[][] tableData = { { "Subject" }, { "" }, { FirstSubject }, { SecondSubject }, { ThirdSubject }, { FourthSubject },

		};

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				String content = tableData[i][j];

				PDType1Font font = (i == 0) ? new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD)
						: new PDType1Font(Standard14Fonts.FontName.HELVETICA);

				contentStream.addRect(initX, initY, cellWidth, -cellHeight);

				contentStream.beginText();
				contentStream.setFont(font, 9);
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.newLineAtOffset(initX + 40, initY - cellHeight + 5);
				contentStream.showText(content);
				contentStream.endText();

				initX += cellWidth;
			}
			initX = 50;
			initY -= cellHeight;
		}

		contentStream.stroke();
		int initX1 = 180;
		int initY1 = 750;
		int cellHeight1 = 20;
		int cellWidth1 = 30;

		int colCount1 = 6;
		int rowCount1 = 6;

		String[] headers = { "My Score", "Rank 1", "Rank 2", "Rank 3", "Rank 4", "Rank 5" };
		String[] subHeaders = { "+ve", "-ve", "+ve", "-ve", "+ve", "-ve", "+ve", "-ve", "+ve", "-ve", "+ve", "-ve" };

		int t1mp = top5MathPositive.get(0).getScore();
		int t2mp = top5MathPositive.get(1).getScore();
		int t3mp = top5MathPositive.get(2).getScore();
		int t4mp = top5MathPositive.get(3).getScore();
		int t5mp = top5MathPositive.get(4).getScore();

		int t1mn = top5MathNegative.get(0).getScore();
		int t2mn = top5MathNegative.get(1).getScore();
		int t3mn = top5MathNegative.get(2).getScore();
		int t4mn = top5MathNegative.get(3).getScore();
		int t5mn = top5MathNegative.get(4).getScore();

		int t1pp = top5PhysicsPositive.get(0).getScore();
		int t2pp = top5PhysicsPositive.get(1).getScore();
		int t3pp = top5PhysicsPositive.get(2).getScore();
		int t4pp = top5PhysicsPositive.get(3).getScore();
		int t5pp = top5PhysicsPositive.get(4).getScore();

		int t1pn = top5PhysicsNegative.get(0).getScore();
		int t2pn = top5PhysicsNegative.get(1).getScore();
		int t3pn = top5PhysicsNegative.get(2).getScore();
		int t4pn = top5PhysicsNegative.get(3).getScore();
		int t5pn = top5PhysicsNegative.get(4).getScore();

		int t1cp = top5ChemPositive.get(0).getScore();
		int t2cp = top5ChemPositive.get(1).getScore();
		int t3cp = top5ChemPositive.get(2).getScore();
		int t4cp = top5ChemPositive.get(3).getScore();
		int t5cp = top5ChemPositive.get(4).getScore();

		int t1cn = top5ChemNegative.get(0).getScore();
		int t2cn = top5ChemNegative.get(1).getScore();
		int t3cn = top5ChemNegative.get(2).getScore();
		int t4cn = top5ChemNegative.get(3).getScore();
		int t5cn = top5ChemNegative.get(4).getScore();

		int t1sp = top5SansPositive.get(0).getScore();
		int t2sp = top5SansPositive.get(1).getScore();
		int t3sp = top5SansPositive.get(2).getScore();
		int t4sp = top5SansPositive.get(3).getScore();
		int t5sp = top5SansPositive.get(4).getScore();

		int t1sn = top5SansNegative.get(0).getScore();
		int t2sn = top5SansNegative.get(1).getScore();
		int t3sn = top5SansNegative.get(2).getScore();
		int t4sn = top5SansNegative.get(3).getScore();
		int t5sn = top5SansNegative.get(4).getScore();

		String[][] tableData1 = {
				
				{ Integer.toString(physicsScore1), Integer.toString(physicsScore2), Integer.toString(t1pp),
						Integer.toString(t1pn), Integer.toString(t2pp), Integer.toString(t2pn), Integer.toString(t3pp),
						Integer.toString(t3pn), Integer.toString(t4pp), Integer.toString(t4pn), Integer.toString(t5pp),
						Integer.toString(t5pn) },
				{ Integer.toString(chemistryScore1), Integer.toString(chemistryScore2), Integer.toString(t1cp),
						Integer.toString(t1cn), Integer.toString(t2cp), Integer.toString(t2cn), Integer.toString(t3cp),
						Integer.toString(t3cn), Integer.toString(t4cp), Integer.toString(t4cn), Integer.toString(t5cp),
						Integer.toString(t5cn) },
				{ Integer.toString(mathsScore1), Integer.toString(mathsScore2), Integer.toString(t1mp),
							Integer.toString(t1mn), Integer.toString(t2mp), Integer.toString(t2mn), Integer.toString(t3mp),
							Integer.toString(t3mn), Integer.toString(t4mp), Integer.toString(t4mn), Integer.toString(t5mp),
							Integer.toString(t5mn) },
				{ Integer.toString(sansScore1), Integer.toString(sansScore2), Integer.toString(t1sp),
						Integer.toString(t1sn), Integer.toString(t2sp), Integer.toString(t2sn), Integer.toString(t3sp),
						Integer.toString(t3sn), Integer.toString(t4sp), Integer.toString(t4sn), Integer.toString(t5sp),
						Integer.toString(t5sn) },

		};

		for (int i = 0; i < headers.length; i++) {
			String content = headers[i];
			contentStream.addRect(initX1, initY1, cellWidth1 * 2, -cellHeight1);
			contentStream.beginText();
			contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 9);
			contentStream.setNonStrokingColor(Color.BLACK);
			contentStream.newLineAtOffset(initX1 + 10, initY1 - cellHeight1 + 5);
			contentStream.showText(content);
			contentStream.endText();
			initX1 += cellWidth1 * 2;
		}
		initX1 = 180;
		initY1 -= cellHeight1;

		for (int i = 0; i < subHeaders.length; i++) {
			String content = subHeaders[i];
			contentStream.addRect(initX1, initY1, cellWidth1, -cellHeight1);
			contentStream.beginText();
			contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 9);
			contentStream.setNonStrokingColor(Color.BLACK);
			contentStream.newLineAtOffset(initX1 + 10, initY1 - cellHeight1 + 5);
			contentStream.showText(content);
			contentStream.endText();
			initX1 += cellWidth1;
		}
		initX1 = 180;
		initY1 -= cellHeight1;

		for (int i = 0; i < tableData1.length; i++) {
			for (int j = 0; j < tableData1[i].length; j++) {
				String content = tableData1[i][j];
				contentStream.addRect(initX1, initY1, cellWidth1, -cellHeight1);
				contentStream.beginText();
				contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 9);
				contentStream.setNonStrokingColor(Color.BLACK);
				contentStream.newLineAtOffset(initX1 + 10, initY1 - cellHeight1 + 5);
				contentStream.showText(content);
				contentStream.endText();
				initX1 += cellWidth1;
			}
			initX1 = 180;
			initY1 -= cellHeight1;
		}
		contentStream.stroke();

	}

}
