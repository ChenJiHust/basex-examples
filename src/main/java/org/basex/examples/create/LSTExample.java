package org.basex.examples.create;

import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.proc.Create;
import org.basex.core.proc.DropDB;
import org.basex.core.proc.XQuery;

/**
 * This example demonstrates how to import a file in the LST format
 * into the database.
 *
 * @author Workgroup DBIS, University of Konstanz 2005-10, ISC License
 * @author Christian Gruen
 */
public final class LSTExample {
  /** Private constructor. */
  private LSTExample() { }

  /**
   * Main test method.
   * @param args command-line arguments
   * @throws BaseXException if a database command fails
   */
  public static void main(final String[] args) throws BaseXException {

    System.out.println("=== LSTExample ===");

    // ------------------------------------------------------------------------
    // Create database context
    final Context ctx = new Context();

    // input file and name of database
    final String file = "etc/example.lst";
    final String name = "lstexample";

    // ------------------------------------------------------------------------
    // Import the specified file
    System.out.println("\n* Import '" + file + "'.");

    new Create(new LSTParser(file), name).execute(ctx);

    // ------------------------------------------------------------------------
    // Perform query
    System.out.println("\n* Number of files:");

    new XQuery("count(//file)").execute(ctx, System.out);

    // ------------------------------------------------------------------------
    // Drop database and close context
    System.out.println("\n\n* Drop database.");

    new DropDB(name).execute(ctx);
    ctx.close();
  }
}